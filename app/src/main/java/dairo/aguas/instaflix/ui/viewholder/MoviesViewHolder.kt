package dairo.aguas.instaflix.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import dairo.aguas.instaflix.databinding.CardMovieBinding
import dairo.aguas.instaflix.ui.adapter.OnListenerDetail
import dairo.aguas.instaflix.ui.detail.DetailFragment
import dairo.aguas.instaflix.ui.model.ItemViewData
import dairo.aguas.instaflix.ui.utils.loadImage

/**
 * Created by Dairo Aguas B on 29/06/2021.
 */
class MoviesViewHolder(
    private val cardMovieBinding: CardMovieBinding,
    private val onListenerDetail: OnListenerDetail
) : RecyclerView.ViewHolder(cardMovieBinding.root) {

    fun bind(movieViewData: ItemViewData) {
        with(cardMovieBinding) {
            tvTitle.text = movieViewData.title
            ivPoster.loadImage(movieViewData.posterPath)
            cvMovie.setOnClickListener {
                onListenerDetail.onClickListener(movieViewData.id)
            }
        }
    }
}