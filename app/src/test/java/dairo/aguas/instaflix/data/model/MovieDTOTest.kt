package dairo.aguas.instaflix.data.model

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Dairo Aguas B on 2/07/2021.
 */
class MovieDTOTest {

    @Test
    fun givenMovieDTOShouldMapToDomain() {
        val movieDTO = MovieDTO(
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

        val domainMovie = movieDTO.toDomainMovie()

        assertEquals(1, domainMovie.id)
        assertEquals(false, domainMovie.adult)
        assertEquals("backdropPath", domainMovie.backdropPath)
        assertEquals("originalLanguage", domainMovie.originalLanguage)
        assertEquals("originalTitle", domainMovie.originalTitle)
        assertEquals("overview", domainMovie.overview)
        assertEquals(10.0, domainMovie.popularity, 0.0)
        assertEquals("posterPath", domainMovie.posterPath)
        assertEquals("releaseDate", domainMovie.releaseDate)
        assertEquals("title", domainMovie.title)
        assertEquals(false, domainMovie.video)
        assertEquals(1000.0, domainMovie.voteAverage, 0.0)
        assertEquals(1000, domainMovie.voteCount)

    }
}