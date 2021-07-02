package dairo.aguas.instaflix.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dairo.aguas.instaflix.domain.exception.NoConnectivityDomainException
import dairo.aguas.instaflix.domain.model.Movie
import dairo.aguas.instaflix.domain.model.Result
import dairo.aguas.instaflix.domain.model.Serie
import dairo.aguas.instaflix.domain.usecase.GetMovieDetailUseCase
import dairo.aguas.instaflix.domain.usecase.GetSerieDetailUseCase
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
class DetailViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var detailViewModel: DetailViewModel
    private val getMovieDetailUseCase = mockk<GetMovieDetailUseCase>(relaxed = true)
    private val getSerieDetailUseCase = mockk<GetSerieDetailUseCase>(relaxed = true)

    @Before
    fun setup() {
        detailViewModel = DetailViewModel(
            getMovieDetailUseCase,
            getSerieDetailUseCase,
            mainCoroutineRule.testDispatcher
        )
    }

    @Test
    fun whenGetMovieDetailIsCalledShouldReturnSuccessState() = mainCoroutineRule.runBlockingTest {
        val randomNumber = (0..100).random()
        val flowMovie = flowOf(Result.Success(Mocks.MOVIE_MOCK))

        //given
        coEvery { getMovieDetailUseCase.invoke(randomNumber) } returns flowMovie

        //when
        val result = arrayListOf<DetailState>()
        val jop = launch {
            detailViewModel.state.toList(result)
        }

        detailViewModel.getMovieDetail(randomNumber)

        //then
        assertTrue(result[0] is DetailState.Loading)
        assertTrue(result[1] is DetailState.Success)
        coVerify(exactly = 1) { getMovieDetailUseCase.invoke(randomNumber) }
        jop.cancel()
    }

    @Test
    fun whenGetMovieDetailIsCalledShouldReturnErrorState() = mainCoroutineRule.runBlockingTest {
        val randomNumber = (0..100).random()
        val flowMovie: Flow<Result<Movie>> = flowOf(Result.Failure(NoConnectivityDomainException))

        //given
        coEvery { getMovieDetailUseCase.invoke(randomNumber) } returns flowMovie

        //when
        val result = arrayListOf<DetailState>()
        val jop = launch {
            detailViewModel.state.toList(result)
        }

        detailViewModel.getMovieDetail(randomNumber)

        //then
        assertTrue(result[0] is DetailState.Loading)
        assertTrue(result[1] is DetailState.Error)
        coVerify(exactly = 1) { getMovieDetailUseCase.invoke(randomNumber) }
        jop.cancel()
    }

    @Test
    fun whenGetMovieDetailIsCalledShouldReturnException() = mainCoroutineRule.runBlockingTest {
        val randomNumber = (0..100).random()
        val flowMovie = flow<Result<Movie>> {
            throw NoConnectivityDomainException
        }

        //given
        coEvery { getMovieDetailUseCase.invoke(randomNumber) } returns flowMovie

        //when
        val result = arrayListOf<DetailState>()
        val jop = launch {
            detailViewModel.state.toList(result)
        }

        detailViewModel.getMovieDetail(randomNumber)

        //then
        assertTrue(result[0] is DetailState.Loading)
        assertTrue(result[1] is DetailState.Error)
        coVerify(exactly = 1) { getMovieDetailUseCase.invoke(randomNumber) }
        jop.cancel()
    }


    @Test
    fun whenGetSerieDetailIsCalledShouldReturnSuccessState() = mainCoroutineRule.runBlockingTest {
        val randomNumber = (0..100).random()
        val flowSeries = flowOf(Result.Success(Mocks.SERIE_MOCK))

        //given
        coEvery { getSerieDetailUseCase.invoke(randomNumber) } returns flowSeries

        //when
        val result = arrayListOf<DetailState>()
        val jop = launch {
            detailViewModel.state.toList(result)
        }

        detailViewModel.getSerieDetail(randomNumber)

        //then
        assertTrue(result[0] is DetailState.Loading)
        assertTrue(result[1] is DetailState.Success)
        coVerify(exactly = 1) { getSerieDetailUseCase.invoke(randomNumber) }
        jop.cancel()
    }

    @Test
    fun whenGetSerieDetailIsCalledShouldReturnErrorState() = mainCoroutineRule.runBlockingTest {
        val randomNumber = (0..100).random()
        val flowSeries: Flow<Result<Serie>> = flowOf(Result.Failure(NoConnectivityDomainException))

        //given
        coEvery { getSerieDetailUseCase.invoke(randomNumber) } returns flowSeries

        //when
        val result = arrayListOf<DetailState>()
        val jop = launch {
            detailViewModel.state.toList(result)
        }

        detailViewModel.getSerieDetail(randomNumber)

        //then
        assertTrue(result[0] is DetailState.Loading)
        assertTrue(result[1] is DetailState.Error)
        coVerify(exactly = 1) { getSerieDetailUseCase.invoke(randomNumber) }
        jop.cancel()
    }

    @Test
    fun whenGetSerieDetailIsCalledShouldReturnException() = mainCoroutineRule.runBlockingTest {
        val randomNumber = (0..100).random()
        val flowSeries = flow<Result<Serie>> {
            throw NoConnectivityDomainException
        }

        //given
        coEvery { getSerieDetailUseCase.invoke(randomNumber) } returns flowSeries

        //when
        val result = arrayListOf<DetailState>()
        val jop = launch {
            detailViewModel.state.toList(result)
        }

        detailViewModel.getSerieDetail(randomNumber)

        //then
        assertTrue(result[0] is DetailState.Loading)
        assertTrue(result[1] is DetailState.Error)
        coVerify(exactly = 1) { getSerieDetailUseCase.invoke(randomNumber) }
        jop.cancel()
    }
}