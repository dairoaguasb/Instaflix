package dairo.aguas.instaflix.data.repository

import dairo.aguas.instaflix.data.endpoints.MovieAPI
import dairo.aguas.instaflix.data.model.MoviesDTO
import dairo.aguas.instaflix.domain.exception.NoConnectivityDomainException
import dairo.aguas.instaflix.domain.model.getSuccess
import dairo.aguas.instaflix.domain.model.isSuccess
import dairo.aguas.instaflix.mock.Mocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

/**
 * Created by Dairo Aguas B on 2/07/2021.
 */
class MovieRepositoryImplTest {

    private lateinit var movieRepository: MovieRepositoryImpl
    private val movieAPI = mockk<MovieAPI>(relaxed = true)

    @Before
    fun setup() {
        movieRepository = MovieRepositoryImpl(movieAPI, "", ExceptionMovieRepositoryImpl())
    }

    @Test
    fun whenGetMoviesPopularIsCalledShouldReturnMovies(): Unit = runBlocking {
        val flowMoviesDTO = flowOf(Mocks.MOVIES_DTO_MOCK)

        //given
        coEvery { movieAPI.getMoviesPopular("") } returns flowMoviesDTO

        //when
        val moviesResult = movieRepository.getMoviesPopular()

        //then
        moviesResult.collect { result ->
            assert(result.isSuccess())
            assertEquals(result.getSuccess(), Mocks.MOVIES_DTO_MOCK.toDomainMovies())
        }

        coVerify(exactly = 1) {
            movieAPI.getMoviesPopular("")
        }
    }

    @Test
    fun whenGetMoviesPopularIsCalledShouldReturnException(): Unit = runBlocking {
        val flowMoviesDTO = flow<MoviesDTO> {
            throw UnknownHostException()
        }

        //given
        coEvery { movieAPI.getMoviesPopular("") } returns flowMoviesDTO

        //when
        val moviesResult = movieRepository.getMoviesPopular()

        //then
        moviesResult.catch {
            assert(it is NoConnectivityDomainException)
        }.collect()

        coVerify(exactly = 1) {
            movieAPI.getMoviesPopular("")
        }
    }
}