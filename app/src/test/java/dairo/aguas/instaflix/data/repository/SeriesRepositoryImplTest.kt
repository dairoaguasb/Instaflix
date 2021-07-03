package dairo.aguas.instaflix.data.repository

import dairo.aguas.instaflix.data.endpoints.SerieAPI
import dairo.aguas.instaflix.data.model.SeriesDTO
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
class SeriesRepositoryImplTest {

    private lateinit var seriesRepository: SeriesRepositoryImpl
    private val serieAPI = mockk<SerieAPI>(relaxed = true)

    @Before
    fun setup() {
        seriesRepository = SeriesRepositoryImpl(serieAPI, "", ExceptionSeriesRepositoryImpl())
    }

    @Test
    fun whenGetSeriesPopularIsCalledReturnMovies(): Unit = runBlocking {
        val flowSeriesDTO = flowOf(Mocks.SERIES_DTO_MOCK)

        //given
        coEvery { serieAPI.getSeriesPopular("") } returns flowSeriesDTO

        //when
        val seriesResult = seriesRepository.getSeriesPopular()

        //then
        seriesResult.collect { result ->
            assert(result.isSuccess())
            assertEquals(result.getSuccess(), Mocks.SERIES_DTO_MOCK.toDomainSeries())
        }

        coVerify(exactly = 1) {
            serieAPI.getSeriesPopular("")
        }
    }

    @Test
    fun whenGetSeriesPopularIsCalledReturnException(): Unit = runBlocking {
        val flowSeriesDTO = flow<SeriesDTO> {
            throw UnknownHostException()
        }

        //given
        coEvery { serieAPI.getSeriesPopular("") } returns flowSeriesDTO

        //when
        val seriesResult = seriesRepository.getSeriesPopular()

        //then
        seriesResult.catch {
            assert(it is NoConnectivityDomainException)
        }.collect()

        coVerify(exactly = 1) {
            serieAPI.getSeriesPopular("")
        }
    }
}