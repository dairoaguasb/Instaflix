package dairo.aguas.instaflix.ui.utils

import android.widget.ImageView
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