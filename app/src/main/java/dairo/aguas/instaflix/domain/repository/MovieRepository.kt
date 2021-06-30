package dairo.aguas.instaflix.domain.repository

import dairo.aguas.instaflix.domain.model.Movies
import dairo.aguas.instaflix.domain.model.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
interface MovieRepository {

    fun getMoviesPopular(apiKey: String): Flow<Result<Movies>>

    fun getMoviesLatest(apiKey: String): Flow<Result<Movies>>

    fun getMoviesTopRated(apiKey: String): Flow<Result<Movies>>
}