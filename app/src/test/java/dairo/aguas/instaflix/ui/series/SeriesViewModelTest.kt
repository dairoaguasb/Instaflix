package dairo.aguas.instaflix.ui.series

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dairo.aguas.instaflix.domain.exception.NoConnectivityDomainException
import dairo.aguas.instaflix.domain.model.Result
import dairo.aguas.instaflix.domain.model.Series
import dairo.aguas.instaflix.domain.usecase.*
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
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Dairo Aguas B on 2/07/2021.
 */
@ExperimentalCoroutinesApi
class SeriesViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var seriesViewModel: SeriesViewModel

    private val getSeriesOnAirUseCase = mockk<GetSeriesOnAirUseCase>(relaxed = true)
    private val getSeriesPopularUseCase = mockk<GetSeriesPopularUseCase>(relaxed = true)
    private val getSeriesTopRatedUseCase = mockk<GetSeriesTopRatedUseCase>(relaxed = true)

    @Before
    fun setup() {
        seriesViewModel = SeriesViewModel(
            getSeriesOnAirUseCase,
            getSeriesPopularUseCase,
            getSeriesTopRatedUseCase,
            mainCoroutineRule.testDispatcher
        )
    }

    @Test
    fun whenGetSeriesPopularIsCalledShouldReturnSuccessState() = mainCoroutineRule.runBlockingTest {
        val flowSeries = flowOf(Result.Success(Mocks.SERIES_MOCK))

        //given
        coEvery { getSeriesPopularUseCase.invoke() } returns flowSeries

        //when
        val result = arrayListOf<SeriesState>()
        val jop = launch {
            seriesViewModel.state.toList(result)
        }

        seriesViewModel.getSeriesPopular()

        //then
        assertTrue(result[0] is SeriesState.Loading)
        assertTrue(result[1] is SeriesState.Success)
        coVerify(exactly = 1) { getSeriesPopularUseCase.invoke() }
        jop.cancel()
    }

    @Test
    fun whenGetSeriesPopularIsCalledShouldReturnErrorState() = mainCoroutineRule.runBlockingTest {
        val flowSeries: Flow<Result<Series>> = flowOf(Result.Failure(NoConnectivityDomainException))

        //given
        coEvery { getSeriesPopularUseCase.invoke() } returns flowSeries

        //when
        val result = arrayListOf<SeriesState>()
        val jop = launch {
            seriesViewModel.state.toList(result)
        }

        seriesViewModel.getSeriesPopular()

        //then
        assertTrue(result[0] is SeriesState.Loading)
        assertTrue(result[1] is SeriesState.Error)
        coVerify(exactly = 1) { getSeriesPopularUseCase.invoke() }
        jop.cancel()
    }

    @Test
    fun whenGetSeriesPopularIsCalledShouldReturnException() = mainCoroutineRule.runBlockingTest {
        val flowSeries = flow<Result<Series>> {
            throw NoConnectivityDomainException
        }

        //given
        coEvery { getSeriesPopularUseCase.invoke() } returns flowSeries

        //when
        val result = arrayListOf<SeriesState>()
        val jop = launch {
            seriesViewModel.state.toList(result)
        }

        seriesViewModel.getSeriesPopular()

        //then
        assertTrue(result[0] is SeriesState.Loading)
        assertTrue(result[1] is SeriesState.Error)
        coVerify(exactly = 1) { getSeriesPopularUseCase.invoke() }
        jop.cancel()
    }
}