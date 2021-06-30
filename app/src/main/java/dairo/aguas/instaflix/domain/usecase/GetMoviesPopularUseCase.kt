package dairo.aguas.instaflix.domain.usecase

import dairo.aguas.instaflix.domain.model.Movies
import dairo.aguas.instaflix.domain.model.Result
import dairo.aguas.instaflix.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
class GetMoviesPopularUseCase(private val movieRepository: MovieRepository) {

    operator fun invoke(): Flow<Result<Movies>> =
        movieRepository.getMoviesPopular()
}