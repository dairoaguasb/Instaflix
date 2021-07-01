package dairo.aguas.instaflix.domain.usecase

import dairo.aguas.instaflix.domain.model.Movie
import dairo.aguas.instaflix.domain.model.Result
import dairo.aguas.instaflix.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Dairo Aguas B on 1/07/2021.
 */
class GetMovieDetailUseCase(private val movieRepository: MovieRepository) {

    operator fun invoke(id: Int): Flow<Result<Movie>> =
        movieRepository.getMovieDetail(id)
}