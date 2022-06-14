package dairo.aguas.instaflix.data.repository

import arrow.core.left
import arrow.core.right
import dairo.aguas.instaflix.data.endpoints.MovieAPI
import dairo.aguas.instaflix.domain.model.Movie
import dairo.aguas.instaflix.domain.model.Movies
import dairo.aguas.instaflix.domain.model.Result
import dairo.aguas.instaflix.domain.repository.DomainExceptionRepository
import dairo.aguas.instaflix.domain.repository.MovieRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
class MovieRepositoryImpl(
    private val movieAPI: MovieAPI,
    private val apiKey: String,
    private val domainExceptionRepository: DomainExceptionRepository
) : MovieRepository {

    override fun getMoviesPopular() = flow<Result<Movies>> {
        val apiResult = movieAPI.getMoviesPopular(apiKey)
        emit(apiResult.toDomainMovies().right())
    }.catch {
        emit(domainExceptionRepository.manageError(it).left())
    }

    override fun getMovieDetail(id: Int) = flow<Result<Movie>> {
        val apiResult = movieAPI.getMovieDetail(id, apiKey)
        emit(apiResult.toDomainMovie().right())
    }.catch {
        emit(domainExceptionRepository.manageError(it).left())
    }
}
