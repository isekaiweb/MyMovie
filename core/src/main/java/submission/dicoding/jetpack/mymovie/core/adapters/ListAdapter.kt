package submission.dicoding.jetpack.mymovie.core.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import submission.dicoding.jetpack.mymovie.core.databinding.ListModelBinding
import submission.dicoding.jetpack.mymovie.core.domain.model.AllData
import submission.dicoding.jetpack.mymovie.core.util.Function.glide

class ListAdapter : PagingDataAdapter<AllData, ListAdapter.ListViewHolder>(DIFF_CALLBACK) {

    private var onItemClickListener: ((AllData) -> Unit)? = null

    fun setOnItemClickListener(listener: (AllData) -> Unit) {
        onItemClickListener = listener
    }


    inner class ListViewHolder(private val binding: ListModelBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieEntity: AllData) {
            binding.apply {
                movieEntity.apply {
                    itemView.glide(poster_path, ivPoster)
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

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AllData>() {
            override fun areItemsTheSame(old: AllData, aNew: AllData): Boolean =
                old.id == aNew.id

            override fun areContentsTheSame(old: AllData, aNew: AllData): Boolean =
                old == aNew
        }
    }
}