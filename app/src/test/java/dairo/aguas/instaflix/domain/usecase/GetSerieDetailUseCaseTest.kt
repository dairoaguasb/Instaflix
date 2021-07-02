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
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by Dairo Aguas B on 2/07/2021.
 */
class GetSerieDetailUseCaseTest {

    private lateinit var getSerieDetailUseCase: GetSerieDetailUseCase
    private val serieRepository = mockk<SerieRepository>(relaxed = true)

    @Before
    fun setup() {
        getSerieDetailUseCase = GetSerieDetailUseCase(serieRepository)
    }

    @Test
    fun whenUseCaseIsCalledShouldReturnSerie(): Unit = runBlocking {
        val randomNumber = (0..100).random()
        val serie = mockk<Serie>()
        val flowSerie = flowOf(Result.Success(serie))

        //given
        coEvery { serieRepository.getSerieDetail(randomNumber) } returns flowSerie

        //when
        val serieResult = getSerieDetailUseCase.invoke(randomNumber)

        //then
        serieResult.collect { result ->
            assert(result.isSuccess())
            Assert.assertEquals(result.getSuccess(), serie)
        }
        coVerify(exactly = 1) {
            serieRepository.getSerieDetail(randomNumber)
        }
    }

    @Test
    fun whenUseCaseIsCalledShouldReturnAnException(): Unit = runBlocking {
        val randomNumber = (0..100).random()
        val flowSerie: Flow<Result<Serie>> = flowOf(Result.Failure(NoConnectivityDomainException))

        //given
        coEvery { serieRepository.getSerieDetail(randomNumber) } returns flowSerie

        //when
        val serieResult = getSerieDetailUseCase.invoke(randomNumber)

        //then
        serieResult.collect { result ->
            assert(result.isFailure())
            Assert.assertEquals(result.getFailure(), NoConnectivityDomainException)
        }
        coVerify(exactly = 1) {
            serieRepository.getSerieDetail(randomNumber)
        }
    }
}