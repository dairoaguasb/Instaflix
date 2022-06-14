package dairo.aguas.instaflix.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dairo.aguas.instaflix.domain.exception.NoConnectivityDomainException
import dairo.aguas.instaflix.domain.model.Movies
import dairo.aguas.instaflix.domain.model.Result
import dairo.aguas.instaflix.domain.usecase.GetMoviesPopularUseCase
import dairo.aguas.instaflix.mock.Mocks
import dairo.aguas.instaflix.utils.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Dairo Aguas B on 2/07/2021.
 */
@ExperimentalCoroutinesApi
class MoviesViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var moviesViewModel: MoviesViewModel

    private val getMoviesUpcomingUseCase = mockk<GetMoviesUpcomingUseCase>(relaxed = true)
    private val getMoviesPopularUseCase = mockk<GetMoviesPopularUseCase>(relaxed = true)
    private val getMoviesTopRatedUseCase = mockk<GetMoviesTopRatedUseCase>(relaxed = true)

    @Before
    fun setup() {
        moviesViewModel = MoviesViewModel(
            getMoviesUpcomingUseCase,
            getMoviesPopularUseCase,
            getMoviesTopRatedUseCase,
            mainCoroutineRule.testDispatcher
        )
    }

    @Test
    fun whenGetMoviesPopularIsCalledShouldReturnSuccessState() = mainCoroutineRule.runBlockingTest {
        val flowMovies = flowOf(Result.Success(Mocks.MOVIES_MOCK))

        //given
        coEvery { getMoviesPopularUseCase.invoke() } returns flowMovies

        //when
        val result = arrayListOf<MoviesState>()
        val jop = launch {
            moviesViewModel.state.toList(result)
        }

        moviesViewModel.getMoviesPopular()

        //then
        assertTrue(result[0] is MoviesState.Loading)
        assertTrue(result[1] is MoviesState.Success)
        coVerify(exactly = 1) { getMoviesPopularUseCase.invoke() }
        jop.cancel()
    }

    @Test
    fun whenGetMoviesPopularIsCalledShouldReturnErrorState() = mainCoroutineRule.runBlockingTest {
        val flowMovies: Flow<Result<Movies>> = flowOf(Result.Failure(NoConnectivityDomainException))

        //given
        coEvery { getMoviesPopularUseCase.invoke() } returns flowMovies

        //when
        val result = arrayListOf<MoviesState>()
        val jop = launch {
            moviesViewModel.state.toList(result)
        }

        moviesViewModel.getMoviesPopular()

        //then
        assertTrue(result[0] is MoviesState.Loading)
        assertTrue(result[1] is MoviesState.Error)
        coVerify(exactly = 1) { getMoviesPopularUseCase.invoke() }
        jop.cancel()
    }

    @Test
    fun whenGetMoviesPopularIsCalledShouldReturnException() = mainCoroutineRule.runBlockingTest {
        val flowMovies = flow<Result<Movies>> {
            throw NoConnectivityDomainException
        }

        //given
        coEvery { getMoviesPopularUseCase.invoke() } returns flowMovies

        //when
        val result = arrayListOf<MoviesState>()
        val jop = launch {
            moviesViewModel.state.toList(result)
        }

        moviesViewModel.getMoviesPopular()

        //then
        assertTrue(result[0] is MoviesState.Loading)
        assertTrue(result[1] is MoviesState.Error)
        coVerify(exactly = 1) { getMoviesPopularUseCase.invoke() }
        jop.cancel()
    }
}