package dairo.aguas.instaflix.domain.usecase

import dairo.aguas.instaflix.domain.exception.NoConnectivityDomainException
import dairo.aguas.instaflix.domain.model.*
import dairo.aguas.instaflix.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by Dairo Aguas B on 2/07/2021.
 */
class GetMoviesUpcomingUseCaseTest {

    private lateinit var getMoviesUpcomingUseCase: GetMoviesUpcomingUseCase
    private val movieRepository = mockk<MovieRepository>(relaxed = true)

    @Before
    fun setup() {
        getMoviesUpcomingUseCase = GetMoviesUpcomingUseCase(movieRepository)
    }

    @Test
    fun whenUseCaseIsCalledShouldReturnMoviesUpcoming(): Unit = runBlocking {
        val movies = mockk<Movies>()
        val flowMovies = flowOf(Result.Success(movies))

        //given
        coEvery { movieRepository.getMoviesUpcoming() } returns flowMovies

        //when
        val moviesResult = getMoviesUpcomingUseCase.invoke()

        //then
        moviesResult.collect { result ->
            assert(result.isSuccess())
            assertEquals(result.getSuccess(), movies)
        }

        coVerify(exactly = 1) {
            movieRepository.getMoviesUpcoming()
        }
    }

    @Test
    fun whenUseCaseIsCalledShouldReturnAnException(): Unit = runBlocking {
        val flowMovies: Flow<Result<Movies>> = flowOf(Result.Failure(NoConnectivityDomainException))

        //given
        coEvery { movieRepository.getMoviesUpcoming() } returns flowMovies

        //when
        val moviesResult = getMoviesUpcomingUseCase.invoke()

        //then
        moviesResult.collect { result ->
            assert(result.isFailure())
            assertEquals(result.getFailure(), NoConnectivityDomainException)
        }

        coVerify(exactly = 1) {
            movieRepository.getMoviesUpcoming()
        }
    }
}