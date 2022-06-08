package dairo.aguas.instaflix.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import dairo.aguas.instaflix.databinding.CardMovieBinding
import dairo.aguas.instaflix.ui.model.ItemViewData
import dairo.aguas.instaflix.ui.viewholder.MoviesViewHolder

/**
 * Created by Dairo Aguas B on 29/06/2021.
 */
class MoviesAdapter(private val onListenerDetail: OnListenerDetail) :
    ListAdapter<ItemViewData, MoviesViewHolder>(MoviesDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            CardMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onListenerDetail
        )
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object MoviesDiffCallback : DiffUtil.ItemCallback<ItemViewData>() {
    override fun areItemsTheSame(oldItem: ItemViewData, newItem: ItemViewData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ItemViewData, newItem: ItemViewData): Boolean {
        return oldItem == newItem
    }
}