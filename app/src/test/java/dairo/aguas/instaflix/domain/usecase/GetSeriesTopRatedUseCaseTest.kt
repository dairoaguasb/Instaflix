package dairo.aguas.instaflix.domain.usecase

import dairo.aguas.instaflix.domain.exception.NoConnectivityDomainException
import dairo.aguas.instaflix.domain.model.*
import dairo.aguas.instaflix.domain.repository.SerieRepository
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
class GetSeriesTopRatedUseCaseTest {

    private lateinit var getSeriesTopRatedUseCase: GetSeriesTopRatedUseCase
    private val serieRepository = mockk<SerieRepository>(relaxed = true)

    @Before
    fun setup() {
        getSeriesTopRatedUseCase = GetSeriesTopRatedUseCase(serieRepository)
    }

    @Test
    fun whenUseCaseIsCalledShouldReturnSeriesTopRated(): Unit = runBlocking {
        val series = mockk<Series>()
        val flowSeries = flowOf(Result.Success(series))

        //given
        coEvery { serieRepository.getSeriesTopRated() } returns flowSeries

        //when
        val seriesResult = getSeriesTopRatedUseCase.invoke()

        //then
        seriesResult.collect { result ->
            assert(result.isSuccess())
            assertEquals(result.getSuccess(), series)
        }
        coVerify(exactly = 1) {
            serieRepository.getSeriesTopRated()
        }
    }

    @Test
    fun whenUseCaseIsCalledShouldReturnAnException(): Unit = runBlocking {
        val flowSeries: Flow<Result<Series>> = flowOf(Result.Failure(NoConnectivityDomainException))

        //given
        coEvery { serieRepository.getSeriesTopRated() } returns flowSeries

        //when
        val serieResult = getSeriesTopRatedUseCase.invoke()

        //then
        serieResult.collect { result ->
            assert(result.isFailure())
            assertEquals(result.getFailure(), NoConnectivityDomainException)
        }
        coVerify(exactly = 1) {
            serieRepository.getSeriesTopRated()
        }
    }
}