package submission.dicoding.jetpack.mymovie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import submission.dicoding.jetpack.mymovie.databinding.ListModelBinding
import submission.dicoding.jetpack.mymovie.models.MovieEntity
import submission.dicoding.jetpack.mymovie.util.Constants.BASE_URL_IMG
import javax.inject.Inject


class ListAdapter @Inject constructor(
    val glide: RequestManager
) : PagingDataAdapter<MovieEntity, ListAdapter.ListViewHolder>(DIFF_CALLBACK) {

    private var onItemClickListener: ((MovieEntity) -> Unit)? = null

    fun setOnItemClickListener(listener: (MovieEntity) -> Unit) {
        onItemClickListener = listener
    }


    inner class ListViewHolder(private val binding: ListModelBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieEntity: MovieEntity) {
            binding.apply {
                movieEntity.apply {
                    glide.load("${BASE_URL_IMG}${poster_path}")
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(ivPoster)
                    tvTitle.text = title ?: name
                    tvOverview.text = overview

                    itemView.setOnClickListener {
                        onItemClickListener?.let { click -> click(this) }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ListAdapter.ListViewHolder =
        ListViewHolder(
            ListModelBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(old: MovieEntity, aNew: MovieEntity): Boolean =
                old.id == aNew.id

            override fun areContentsTheSame(old: MovieEntity, aNew: MovieEntity): Boolean =
                old == aNew
        }
    }
}