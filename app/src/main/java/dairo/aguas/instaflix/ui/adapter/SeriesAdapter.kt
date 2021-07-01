package dairo.aguas.instaflix.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import dairo.aguas.instaflix.databinding.CardSerieBinding
import dairo.aguas.instaflix.ui.model.SerieViewData
import dairo.aguas.instaflix.ui.viewholder.SeriesViewHolder

/**
 * Created by Dairo Aguas B on 29/06/2021.
 */
class SeriesAdapter(private val onListenerDetail: OnListenerDetail) :
    ListAdapter<SerieViewData, SeriesViewHolder>(SeriesDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        return SeriesViewHolder(
            CardSerieBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onListenerDetail
        )
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object SeriesDiffCallback : DiffUtil.ItemCallback<SerieViewData>() {
    override fun areItemsTheSame(oldItem: SerieViewData, newItem: SerieViewData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SerieViewData, newItem: SerieViewData): Boolean {
        return oldItem == newItem
    }
}