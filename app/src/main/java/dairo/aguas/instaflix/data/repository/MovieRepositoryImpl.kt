package dairo.aguas.instaflix.data.repository

import dairo.aguas.instaflix.domain.model.Movie
import dairo.aguas.instaflix.domain.model.MovieResponse
import dairo.aguas.instaflix.domain.model.Result
import dairo.aguas.instaflix.domain.repository.MovieRepository
import kotlinx.coroutines.flow.flow

/**
 * Created by Dairo Aguas B on 30/06/2021.
 */
class MovieRepositoryImpl : MovieRepository {

    override fun getMoviesPopular() = flow<Result<MovieResponse>> {
        emit(
            Result.Success(
                MovieResponse(
                    page = 1,
                    movieList = mutableListOf(
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

    override fun getMoviesLatest() = flow<Result<MovieResponse>> {
        emit(
            Result.Success(
                MovieResponse(
                    page = 1,
                    movieList = mutableListOf(
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

    override fun getMoviesTopRated() = flow<Result<MovieResponse>> {
        emit(
            Result.Success(
                MovieResponse(
                    page = 1,
                    movieList = mutableListOf(
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

private val movieMock = Movie(
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