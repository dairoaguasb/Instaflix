package dairo.aguas.instaflix.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import dairo.aguas.instaflix.ui.model.ItemViewData
import dairo.aguas.instaflix.ui.ui.theme.InstaflixTheme

@Composable
fun CardListItem(itemViewData: ItemViewData) {
    Box(
        modifier = Modifier.padding(4.dp)
    ) {
        Card(
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Box {
                Image(
                    painter = rememberImagePainter(data = itemViewData.posterPath),
                    contentDescription = itemViewData.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .aspectRatio(0.6f)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .background(Color.Black.copy(alpha = 0.5f))
                        .align(Alignment.BottomCenter)
                ) {
                    Text(
                        color = Color.White,
                        text = itemViewData.title,
                        style = MaterialTheme.typography.subtitle1,
                        textAlign = TextAlign.Center,
                        maxLines = 2,
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            MaterialTheme.colors.primaryVariant,
                            MaterialTheme.colors.primary,
                        )
                    )
                )
                .align(Alignment.TopCenter)
        ) {
            Text(
                color = Color.White,
                text = itemViewData.voteAverage,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun CardListItemPreview() {
    InstaflixTheme {
        CardListItem(
            itemViewData = ItemViewData(
                id = 1,
                posterPath = "https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg",
                title = "Joker",
                voteAverage = "7.0"
            )
        )
    }
}
