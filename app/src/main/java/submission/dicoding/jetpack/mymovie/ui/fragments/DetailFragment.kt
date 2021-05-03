package submission.dicoding.jetpack.mymovie.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dagger.hilt.android.AndroidEntryPoint
import submission.dicoding.jetpack.mymovie.R
import submission.dicoding.jetpack.mymovie.databinding.FragmentDetailBinding
import submission.dicoding.jetpack.mymovie.models.MovieResponse
import submission.dicoding.jetpack.mymovie.ui.viewmodels.DetailViewModel
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel: DetailViewModel by hiltNavGraphViewModels(R.id.nav_host)


    @Inject
    lateinit var glide: RequestManager


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)
        subscribeToObserver()
    }

    private fun subscribeToObserver() {
        viewModel.setMovieDetail(args.id)

        viewModel.movieDetail.observe(viewLifecycleOwner, { movie ->
            setupUI(movie)
        })
    }


    private fun setupUI(movie: MovieResponse) {
        binding.apply {
            with(movie) {
                glide.load(poster_url).transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivPoster)
                tvTitle.text = title
                tvOverview.text = overview
                tvDate.text = date_publish
            }
        }
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}