package dairo.aguas.instaflix.mock

import dairo.aguas.instaflix.data.model.MovieDTO
import dairo.aguas.instaflix.data.model.MoviesDTO
import dairo.aguas.instaflix.data.model.SerieDTO
import dairo.aguas.instaflix.data.model.SeriesDTO
import dairo.aguas.instaflix.domain.model.Movie
import dairo.aguas.instaflix.domain.model.Movies
import dairo.aguas.instaflix.domain.model.Serie
import dairo.aguas.instaflix.domain.model.Series

/**
 * Created by Dairo Aguas B on 2/07/2021.
 */
object Mocks {

    val MOVIE_DTO_MOCK = MovieDTO(
        id = 1,
        adult = false,
        backdropPath = "backdropPath",
        originalLanguage = "originalLanguage",
        originalTitle = "originalTitle",
        overview = "overview",
        popularity = 10.0,
        posterPath = "posterPath",
        releaseDate = "releaseDate",
        title = "title",
        video = false,
        voteAverage = 1000.0,
        voteCount = 1000
    )

    val SERIE_DTO_MOCK = SerieDTO(
        id = 1,
        backdropPath = "backdropPath",
        originalLanguage = "originalLanguage",
        overview = "overview",
        popularity = 10.0,
        posterPath = "posterPath",
        title = "title",
        voteAverage = 1000.0,
        voteCount = 1000
    )

    val MOVIE_MOCK = Movie(
        id = 1,
        adult = false,
        backdropPath = "backdropPath",
        originalLanguage = "originalLanguage",
        originalTitle = "originalTitle",
        overview = "overview",
        popularity = 10.0,
        posterPath = "posterPath",
        releaseDate = "releaseDate",
        title = "title",
        video = false,
        voteAverage = 1000.0,
        voteCount = 1000
    )

    val SERIE_MOCK = Serie(
        id = 1,
        backdropPath = "backdropPath",
        originalLanguage = "originalLanguage",
        overview = "overview",
        popularity = 10.0,
        posterPath = "posterPath",
        title = "title",
        voteAverage = 1000.0,
        voteCount = 1000
    )

    val MOVIES_MOCK = Movies(
        page = 1,
        movies = mutableListOf(MOVIE_MOCK, MOVIE_MOCK),
        totalPages = 10,
        totalResults = 1000
    )

    val SERIES_MOCK = Series(
        page = 1,
        series = mutableListOf(SERIE_MOCK, SERIE_MOCK),
        totalPages = 10,
        totalResults = 1000
    )

    val MOVIES_DTO_MOCK = MoviesDTO(
        page = 1,
        movies = mutableListOf(MOVIE_DTO_MOCK, MOVIE_DTO_MOCK),
        totalPages = 10,
        totalResults = 1000
    )

    val SERIES_DTO_MOCK = SeriesDTO(
        page = 1,
        series = mutableListOf(SERIE_DTO_MOCK, SERIE_DTO_MOCK),
        totalPages = 10,
        totalResults = 1000
    )
}