package dairo.aguas.instaflix.ui.model

import dairo.aguas.instaflix.mock.Mocks
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Dairo Aguas B on 2/07/2021.
 */
class MovieViewDataTest {

    @Test
    fun givenADomainMovieShouldMapToMovieViewData() {
        val domainMovie = Mocks.MOVIE_MOCK
        val movieViewData = MovieViewData(domainMovie)

        assertEquals(1, movieViewData.id)
        assertEquals("https://image.tmdb.org/t/p/w185/posterPath", movieViewData.posterPath)
        assertEquals("title", movieViewData.title)
    }
}