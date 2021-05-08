package submission.dicoding.jetpack.mymovie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import submission.dicoding.jetpack.mymovie.databinding.MovieLoadStateFooterBinding
import submission.dicoding.jetpack.mymovie.models.Movie

class MovieLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<MovieLoadStateAdapter.LoadStateViewHolder>() {


    private var onItemClickListener: ((Movie) -> Unit)? = null

    fun setOnItemClickListener(listener: (Movie) -> Unit) {
        onItemClickListener = listener
    }

    inner class LoadStateViewHolder(
        private val binding:
        MovieLoadStateFooterBinding
    ) : RecyclerView.ViewHolder(binding.root) {


        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                ibRefresh.isVisible = loadState is LoadState.Error
                ibRefresh.setOnClickListener {
                    retry.invoke()
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder =
        LoadStateViewHolder(
            MovieLoadStateFooterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) =
        holder.bind(loadState)


}