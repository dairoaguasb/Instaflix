package dairo.aguas.instaflix.data.repository

import dairo.aguas.instaflix.data.model.MovieDTO
import dairo.aguas.instaflix.data.model.MoviesDTO
import dairo.aguas.instaflix.domain.model.Result
import dairo.aguas.instaflix.domain.repository.MovieRepository
import kotlinx.coroutines.flow.flow

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
class MovieRepositoryImpl : MovieRepository {

    override fun getMoviesPopular() = flow<Result<MoviesDTO>> {
        emit(
            Result.Success(
                MoviesDTO(
                    page = 1,
                    movieDTOList = mutableListOf(
                        movieMock,
                        movieMock,
                        movieMock,
                        movieMock,
                        movieMock
                    ),
                    totalPages = 10,
                    totalResults = 100
                )
            )
        )
    }

    override fun getMoviesLatest() = flow<Result<MoviesDTO>> {
        emit(
            Result.Success(
                MoviesDTO(
                    page = 1,
                    movieDTOList = mutableListOf(
                        movieMock,
                        movieMock,
                        movieMock,
                        movieMock,
                        movieMock
                    ),
                    totalPages = 10,
                    totalResults = 100
                )
            )
        )
    }

    override fun getMoviesTopRated() = flow<Result<MoviesDTO>> {
        emit(
            Result.Success(
                MoviesDTO(
                    page = 1,
                    movieDTOList = mutableListOf(
                        movieMock,
                        movieMock,
                        movieMock,
                        movieMock,
                        movieMock
                    ),
                    totalPages = 10,
                    totalResults = 100
                )
            )
        )
    }
}

private val movieMock = MovieDTO(
    id = 123456,
    adult = false,
    backdropPath = "",
    originalLanguage = "Ingles",
    originalTitle = "Wonder Woman",
    overview = "",
    popularity = 1.0,
    posterPath = "",
    releaseDate = "",
    title = "Wonder Woman",
    video = true,
    voteAverage = 7.8,
    voteCount = 1563
)