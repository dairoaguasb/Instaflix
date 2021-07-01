package dairo.aguas.instaflix.ui.utils

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dairo.aguas.instaflix.R

/**
 * Created by Dairo Aguas B on 29/06/2021.
 */
fun ImageView.loadImage(urlImage: String) {
    Glide.with(this.context)
        .load(urlImage)
        .apply(
            RequestOptions().placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
        )
        .into(this)
}

fun RatingBar.format5Start(voteAverage: Double) {
    this.rating = voteAverage.div(2).toFloat()
}

fun TextView.formatVoteCount(voteCount: Int) {
    this.text = voteCount.toString()
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}