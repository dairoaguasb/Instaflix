package dairo.aguas.instaflix.data.repository

import dairo.aguas.instaflix.data.endpoints.SerieAPI
import dairo.aguas.instaflix.domain.model.Result
import dairo.aguas.instaflix.domain.repository.DomainExceptionRepository
import dairo.aguas.instaflix.domain.repository.SerieRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.transform

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
class SeriesRepositoryImpl(
    private val serieAPI: SerieAPI,
    private val apiKey: String,
    private val domainExceptionRepository: DomainExceptionRepository
) : SerieRepository {

    override fun getSeriesPopular() =
        serieAPI.getSeriesPopular(apiKey).transform { apiResult ->
            emit(Result.Success(apiResult.toDomainSeries()))
        }.catch {
            throw domainExceptionRepository.manageError(it)
        }

    override fun getSeriesOnAir() =
        serieAPI.getSeriesOnAir(apiKey).transform { apiResult ->
            emit(Result.Success(apiResult.toDomainSeries()))
        }.catch {
            throw domainExceptionRepository.manageError(it)
        }

    override fun getSeriesTopRated() =
        serieAPI.getSeriesTopRated(apiKey).transform { apiResult ->
            emit(Result.Success(apiResult.toDomainSeries()))
        }.catch {
            throw domainExceptionRepository.manageError(it)
        }

    override fun getSerieDetail(id: Int) =
        serieAPI.getSerieDetail(id, apiKey).transform { apiResult ->
            emit(Result.Success(apiResult.toDomainSerie()))
        }.catch {
            throw domainExceptionRepository.manageError(it)
        }
}