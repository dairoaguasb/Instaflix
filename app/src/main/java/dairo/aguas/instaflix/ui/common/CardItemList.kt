package dairo.aguas.instaflix.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import dairo.aguas.instaflix.ui.model.ItemViewData
import dairo.aguas.instaflix.ui.ui.theme.InstaflixTheme

@Composable
fun CardListItem(itemViewData: ItemViewData) {
    Card(
        elevation = 8.dp,
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = MaterialTheme.colors.onBackground,
        contentColor = MaterialTheme.colors.background,
    ) {
        Column {
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
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(
                    color = Color.White,
                    text = itemViewData.title,
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                )
            }
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
