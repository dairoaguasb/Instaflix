package dairo.aguas.instaflix.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import dairo.aguas.instaflix.databinding.CardMovieBinding
import dairo.aguas.instaflix.ui.model.MovieViewData
import dairo.aguas.instaflix.ui.utils.loadImage

/**
 * Created by Dairo Aguas B on 29/06/2021.
 */
class MoviesViewHolder(private val cardMovieBinding: CardMovieBinding) :
    RecyclerView.ViewHolder(cardMovieBinding.root) {

    fun bind(movieViewData: MovieViewData) {
        with(cardMovieBinding) {
            tvTitle.text = movieViewData.title
            ivPoster.loadImage(movieViewData.posterPath)
        }
    }
}