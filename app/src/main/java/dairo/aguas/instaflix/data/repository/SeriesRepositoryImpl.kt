package dairo.aguas.instaflix.data.repository

import dairo.aguas.instaflix.data.endpoints.SerieAPI
import dairo.aguas.instaflix.domain.model.Result
import dairo.aguas.instaflix.domain.model.Serie
import dairo.aguas.instaflix.domain.model.Series
import dairo.aguas.instaflix.domain.repository.DomainExceptionRepository
import dairo.aguas.instaflix.domain.repository.SerieRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
class SeriesRepositoryImpl(
    private val serieAPI: SerieAPI,
    private val apiKey: String,
    private val domainExceptionRepository: DomainExceptionRepository
) : SerieRepository {

    override fun getSeriesPopular() = flow<Result<Series>> {
        val apiResult = serieAPI.getSeriesPopular(apiKey)
        emit(Result.Success(apiResult.toDomainSeries()))
    }.catch {
        throw domainExceptionRepository.manageError(it)
    }

    override fun getSeriesOnAir() = flow<Result<Series>> {
        val apiResult = serieAPI.getSeriesOnAir(apiKey)
        emit(Result.Success(apiResult.toDomainSeries()))
    }.catch {
        throw domainExceptionRepository.manageError(it)
    }

    override fun getSeriesTopRated() = flow<Result<Series>> {
        val apiResult = serieAPI.getSeriesTopRated(apiKey)
        emit(Result.Success(apiResult.toDomainSeries()))
    }.catch {
        throw domainExceptionRepository.manageError(it)
    }

    override fun getSerieDetail(id: Int) = flow<Result<Serie>> {
        val apiResult = serieAPI.getSerieDetail(id, apiKey)
        emit(Result.Success(apiResult.toDomainSerie()))
    }.catch {
        throw domainExceptionRepository.manageError(it)
    }
}
