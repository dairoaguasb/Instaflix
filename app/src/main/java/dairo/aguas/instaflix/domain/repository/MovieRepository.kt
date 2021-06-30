package dairo.aguas.instaflix.domain.repository

import dairo.aguas.instaflix.domain.model.MovieResponse
import dairo.aguas.instaflix.domain.model.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
interface MovieRepository {

    fun getMoviesPopular(): Flow<Result<MovieResponse>>
}