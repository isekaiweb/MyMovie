package submission.dicoding.jetpack.mymovie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import submission.dicoding.jetpack.mymovie.databinding.FavoriteModelBinding
import submission.dicoding.jetpack.mymovie.models.FavoriteEntity
import submission.dicoding.jetpack.mymovie.util.Constants
import javax.inject.Inject

class FavoriteAdapter @Inject constructor(
    val glide: RequestManager
) : PagingDataAdapter<FavoriteEntity, FavoriteAdapter.FavoriteViewHolder>(DIFF_CALLBACK) {

    private var onItemClickListener: ((FavoriteEntity) -> Unit)? = null

    fun setOnItemClickListener(listener: (FavoriteEntity) -> Unit) {
        onItemClickListener = listener
    }


    inner class FavoriteViewHolder(private val binding: FavoriteModelBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favorite: FavoriteEntity) {
            binding.apply {
                favorite.apply {
                    glide.load("${Constants.BASE_URL_IMG}${poster_path}")
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(ivPoster)
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
    ): FavoriteAdapter.FavoriteViewHolder =
        FavoriteViewHolder(
            FavoriteModelBinding.inflate(
                LayoutInflater.from(parent.context), parent,
                false
            ),
        )


    override fun onBindViewHolder(holder: FavoriteAdapter.FavoriteViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteEntity>() {
            override fun areItemsTheSame(oldFav: FavoriteEntity, newFav: FavoriteEntity): Boolean =
                oldFav.id == newFav.id

            override fun areContentsTheSame(
                oldFav: FavoriteEntity,
                newFav: FavoriteEntity
            ): Boolean =
                oldFav.hashCode() == newFav.hashCode()
        }
    }

}