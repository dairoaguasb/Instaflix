package dairo.aguas.instaflix.mock

import dairo.aguas.instaflix.domain.model.Movie
import dairo.aguas.instaflix.domain.model.Movies
import dairo.aguas.instaflix.domain.model.Serie

/**
 * Created by Dairo Aguas B on 2/07/2021.
 */
object Mocks {

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
}