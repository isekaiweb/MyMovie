package submission.dicoding.jetpack.mymovie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import submission.dicoding.jetpack.mymovie.databinding.ListModelBinding
import submission.dicoding.jetpack.mymovie.models.MovieResponse
import javax.inject.Inject

class ListAdapter @Inject constructor(
    val glide: RequestManager
) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<MovieResponse>() {
        override fun areItemsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieResponse, newItem: MovieResponse): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    var movie: List<MovieResponse>
        get() = differ.currentList
        set(value) = differ.submitList(value)


    private var onItemClickListener: ((MovieResponse) -> Unit)? = null

    fun setOnItemClickListener(listener: (MovieResponse) -> Unit) {
        onItemClickListener = listener
    }


    inner class ListViewHolder(private val binding: ListModelBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieResponse) {
            binding.apply {
                movie.apply {
                    glide.load(poster_url).transition(DrawableTransitionOptions.withCrossFade())
                        .into(ivPoster)
                    tvTitle.text = title
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

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) =
        holder.bind(movie[position])

    override fun getItemCount(): Int = movie.size
}