package dairo.aguas.instaflix.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    Column(
        modifier = Modifier.padding(4.dp)
    ) {
        Card(
            elevation = 2.dp,
        ) {
            Image(
                painter = rememberImagePainter(data = itemViewData.posterPath),
                contentDescription = itemViewData.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .aspectRatio(0.5f)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.background(
                color = MaterialTheme.colors.background
            )
        ) {
            Text(
                text = itemViewData.title,
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center,
                maxLines = 2,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
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
                title = "Joker"
            )
        )
    }
}
