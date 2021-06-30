package dairo.aguas.instaflix.data.repository

import dairo.aguas.instaflix.data.endpoints.SerieAPI
import dairo.aguas.instaflix.domain.model.Result
import dairo.aguas.instaflix.domain.repository.SerieRepository
import kotlinx.coroutines.flow.transform

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
class SeriesRepositoryImpl(private val serieAPI: SerieAPI) : SerieRepository {

    override fun getSeriesPopular(apiKey: String) =
        serieAPI.getSeriesPopular(apiKey).transform { apiResult ->
            emit(Result.Success(apiResult.toDomainSeries()))
        }

    override fun getSeriesOnAir(apiKey: String) =
        serieAPI.getSeriesOnAir(apiKey).transform { apiResult ->
            emit(Result.Success(apiResult.toDomainSeries()))
        }

    override fun getSeriesTopRated(apiKey: String) =
        serieAPI.getSeriesTopRated(apiKey).transform { apiResult ->
            emit(Result.Success(apiResult.toDomainSeries()))
        }
}