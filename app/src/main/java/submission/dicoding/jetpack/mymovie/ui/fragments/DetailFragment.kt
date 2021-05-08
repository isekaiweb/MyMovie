package submission.dicoding.jetpack.mymovie.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dagger.hilt.android.AndroidEntryPoint
import submission.dicoding.jetpack.mymovie.R
import submission.dicoding.jetpack.mymovie.databinding.FragmentDetailBinding
import submission.dicoding.jetpack.mymovie.models.Movie
import submission.dicoding.jetpack.mymovie.ui.viewmodels.DetailViewModel
import submission.dicoding.jetpack.mymovie.util.Constants.BASE_URL_IMG
import submission.dicoding.jetpack.mymovie.util.EventObserver
import submission.dicoding.jetpack.mymovie.util.Function.createToastNetworkError
import submission.dicoding.jetpack.mymovie.util.Status
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

    private fun setupData() {
        val path = args.path
        path.let {
            viewModel.getDetailMovie(it[0], it[1].toInt())
        }

        binding.apply {
            layoutRefresh.setOnRefreshListener {
                subscribeToObserver()
            }

            ibRefresh.setOnClickListener {
                subscribeToObserver()
            }
        }
    }

    private fun subscribeToObserver() {
        val layoutRefresh = binding.layoutRefresh
        val ibRefresh = binding.ibRefresh
        ibRefresh.isVisible = false
        setupData()
        var loading = false
        viewModel.movie.observe(viewLifecycleOwner, EventObserver { response ->
            response.apply {
                when (status) {
                    Status.SUCCESS -> {
                        data?.let { movie -> setupUI(movie) }
                    }
                    Status.ERROR -> {
                        ibRefresh.isVisible = true
                        createToastNetworkError(true, requireContext())
                    }
                    Status.LOADING -> {
                        loading = true
                    }
                }
            }
        })
        layoutRefresh.isRefreshing = loading
    }


    private fun setupUI(movie: Movie) {

        binding.apply {
            with(movie) {
                glide.load("${BASE_URL_IMG}${poster_path}")
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivPoster)
                tvTitle.text = name ?: title
                tvOverview.text = overview
                tvDate.text = release_date ?: first_air_date
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}