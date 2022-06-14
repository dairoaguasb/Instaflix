package dairo.aguas.instaflix.domain.repository

import dairo.aguas.instaflix.domain.model.Movie
import dairo.aguas.instaflix.domain.model.Movies
import dairo.aguas.instaflix.domain.model.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
interface MovieRepository {

    fun getMoviesPopular(): Flow<Result<Movies>>

    fun getMovieDetail(id: Int): Flow<Result<Movie>>
}
