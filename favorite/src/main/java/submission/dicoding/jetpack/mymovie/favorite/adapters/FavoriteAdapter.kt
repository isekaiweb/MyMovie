package submission.dicoding.jetpack.mymovie.favorite.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import submission.dicoding.jetpack.mymovie.core.databinding.FavoriteModelBinding
import submission.dicoding.jetpack.mymovie.core.domain.model.FavoriteData
import submission.dicoding.jetpack.mymovie.core.util.Function.glide

class FavoriteAdapter :
    PagingDataAdapter<FavoriteData, FavoriteAdapter.FavoriteViewHolder>(DIFF_CALLBACK) {

    private var onItemClickListener: ((FavoriteData) -> Unit)? = null

    fun setOnItemClickListener(listener: (FavoriteData) -> Unit) {
        onItemClickListener = listener
    }


    inner class FavoriteViewHolder(private val binding: FavoriteModelBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteData: FavoriteData) {
            binding.apply {
                favoriteData.apply {
                    itemView.glide(poster_path, ivPoster)
                    tvTitle.text = title
                    itemView.setOnClickListener {
                        onItemClickListener?.let { click -> click(this) }
                    }
                }
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteViewHolder =
        FavoriteViewHolder(
            FavoriteModelBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            ),
        )


    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteData>() {
            override fun areItemsTheSame(oldFav: FavoriteData, newFav: FavoriteData): Boolean =
                oldFav.id == newFav.id

            override fun areContentsTheSame(
                oldFav: FavoriteData,
                newFav: FavoriteData
            ): Boolean =
                oldFav.hashCode() == newFav.hashCode()
        }
    }

}