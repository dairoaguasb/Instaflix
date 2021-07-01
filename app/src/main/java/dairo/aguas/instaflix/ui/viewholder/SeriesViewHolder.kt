package dairo.aguas.instaflix.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import dairo.aguas.instaflix.databinding.CardSerieBinding
import dairo.aguas.instaflix.ui.adapter.OnListenerDetail
import dairo.aguas.instaflix.ui.detail.DetailFragment
import dairo.aguas.instaflix.ui.model.SerieViewData
import dairo.aguas.instaflix.ui.utils.loadImage

/**
 * Created by Dairo Aguas B on 29/06/2021.
 */
class SeriesViewHolder(
    private val cardSerieBinding: CardSerieBinding,
    private val onListenerDetail: OnListenerDetail
) : RecyclerView.ViewHolder(cardSerieBinding.root) {

    fun bind(serieViewData: SerieViewData) {
        with(cardSerieBinding) {
            tvTitle.text = serieViewData.name
            ivPoster.loadImage(serieViewData.posterPath)
            cvSerie.setOnClickListener {
                onListenerDetail.onClickListener(serieViewData.id)
            }
        }
    }
}