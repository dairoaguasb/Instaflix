package dairo.aguas.instaflix.data.repository

import dairo.aguas.instaflix.data.endpoints.MovieAPI
import dairo.aguas.instaflix.domain.model.Result
import dairo.aguas.instaflix.domain.repository.MovieRepository
import kotlinx.coroutines.flow.transform

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
class MovieRepositoryImpl(
    private val movieAPI: MovieAPI,
    private val apiKey: String
) : MovieRepository {

    override fun getMoviesPopular() =
        movieAPI.getMoviesPopular(apiKey).transform { apiResult ->
            emit(Result.Success(apiResult.toDomainMovies()))
        }

    override fun getMoviesUpcoming() =
        movieAPI.getMoviesUpcoming(apiKey).transform { apiResult ->
            emit(Result.Success(apiResult.toDomainMovies()))
        }

    override fun getMoviesTopRated() =
        movieAPI.getMoviesTopRated(apiKey).transform { apiResult ->
            emit(Result.Success(apiResult.toDomainMovies()))
        }

    override fun getMovieDetail(id: Int) =
        movieAPI.getMovieDetail(id, apiKey).transform { apiResult ->
            emit(Result.Success(apiResult.toDomainMovie()))
        }
}