package dairo.aguas.instaflix.ui.movies.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import dairo.aguas.instaflix.R
import dairo.aguas.instaflix.domain.model.Movie
import dairo.aguas.instaflix.ui.common.ErrorMessage
import dairo.aguas.instaflix.ui.common.LoadingIndicator
import dairo.aguas.instaflix.ui.model.DetailViewData
import dairo.aguas.instaflix.ui.utils.verticalGradientBackground

@Composable
fun MovieDetailScreen(viewModel: DetailViewModel, movieId: Int) {
    val detailState by viewModel.state.collectAsState()

    LaunchedEffect(key1 = movieId) {
        viewModel.getMovieDetail(movieId)
    }

    MovieDetailState(detailState = detailState) {
        viewModel.getMovieDetail(movieId)
    }
}

@Composable
private fun MovieDetailState(detailState: DetailState, onRefresh: () -> Unit) {
    when {
        detailState.loading -> {
            LoadingIndicator()
        }
        detailState.error != 0 -> {
            ErrorMessage(
                message = stringResource(id = detailState.error),
                onRefresh = onRefresh
            )
        }
        detailState.detailViewData != null -> {
            MovieDetail(detailState.detailViewData)
        }
    }
}

@Composable
private fun MovieDetail(detailViewData: DetailViewData) {
    val colors = listOf(MaterialTheme.colors.primary, Color.Black)
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .verticalGradientBackground(colors)
            .fillMaxSize()
    ) {
        Image(
            painter = rememberAsyncImagePainter(detailViewData.backdropPath),
            contentDescription = detailViewData.title,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .height(280.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = detailViewData.title,
            style = MaterialTheme.typography.h5,
            color = Color.White,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(id = R.string.movies_detail_release, detailViewData.releaseDate),
            style = MaterialTheme.typography.subtitle1,
            color = Color.White,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        )
    }
}

@Preview
@Composable
fun MovieDetailPreview() {
    MovieDetail(
        detailViewData = DetailViewData(
            movie = Movie(
                id = 1,
                adult = false,
                backdropPath = "",
                originalLanguage = "en",
                originalTitle = "Doctor Strange in the Multiverse of Madness",
                overview = "Doctor Strange, with the help of mystical allies both old and new, traverses the mind-bending and dangerous alternate realities of the Multiverse to confront a mysterious new adversary.",
                popularity = 7647.02,
                posterPath = "",
                releaseDate = "2022-05-04",
                title = "Doctor Strange in the Multiverse of Madness",
                video = false,
                voteAverage = 7.6,
                voteCount = 2915
            )
        )
    )
}
