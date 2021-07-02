package dairo.aguas.instaflix.domain.usecase

import dairo.aguas.instaflix.domain.exception.NoConnectivityDomainException
import dairo.aguas.instaflix.domain.exception.UnknownError
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
class GetMovieDetailUseCaseTest {

    private lateinit var getMovieDetailUseCase: GetMovieDetailUseCase
    private val movieRepository = mockk<MovieRepository>(relaxed = true)

    @Before
    fun setup() {
        getMovieDetailUseCase = GetMovieDetailUseCase(movieRepository)
    }

    @Test
    fun whenUseCaseIsCalledShouldReturnMovie(): Unit = runBlocking {
        val randomNumber = (0..100).random()
        val movie = mockk<Movie>()
        val flowMovie = flowOf(Result.Success(movie))

        //given
        coEvery { movieRepository.getMovieDetail(randomNumber) } returns flowMovie

        //when
        val movieResult = getMovieDetailUseCase.invoke(randomNumber)

        //then
        movieResult.collect { result ->
            assert(result.isSuccess())
            assertEquals(result.getSuccess(), movie)
        }
        coVerify(exactly = 1) {
            movieRepository.getMovieDetail(randomNumber)
        }
    }

    @Test
    fun whenUseCaseIsCalledShouldReturnAnException(): Unit = runBlocking {
        val randomNumber = (0..100).random()
        val flowMovie: Flow<Result<Movie>> = flowOf(Result.Failure(NoConnectivityDomainException))

        //given
        coEvery { movieRepository.getMovieDetail(randomNumber) } returns flowMovie

        //when
        val movieResult = getMovieDetailUseCase.invoke(randomNumber)

        //then
        movieResult.collect { result ->
            assert(result.isFailure())
            assertEquals(result.getFailure(), NoConnectivityDomainException)
        }
        coVerify(exactly = 1) {
            movieRepository.getMovieDetail(randomNumber)
        }
    }
}