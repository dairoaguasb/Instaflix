package dairo.aguas.instaflix.domain.repository

import dairo.aguas.instaflix.domain.model.Result
import dairo.aguas.instaflix.domain.model.Serie
import dairo.aguas.instaflix.domain.model.Series
import kotlinx.coroutines.flow.Flow

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
interface SerieRepository {

    fun getSeriesPopular(): Flow<Result<Series>>

    fun getSerieDetail(id: Int): Flow<Result<Serie>>
}