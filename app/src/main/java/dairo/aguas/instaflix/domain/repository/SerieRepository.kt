package dairo.aguas.instaflix.domain.repository

import dairo.aguas.instaflix.domain.model.Result
import dairo.aguas.instaflix.domain.model.Series
import kotlinx.coroutines.flow.Flow

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
interface SerieRepository {

    fun getSeriesPopular(apiKey: String): Flow<Result<Series>>

    fun getSeriesOnAir(apiKey: String): Flow<Result<Series>>

    fun getSeriesTopRated(apiKey: String): Flow<Result<Series>>
}