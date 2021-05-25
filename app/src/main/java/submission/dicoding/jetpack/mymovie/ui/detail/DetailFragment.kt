package submission.dicoding.jetpack.mymovie.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import submission.dicoding.jetpack.mymovie.R
import submission.dicoding.jetpack.mymovie.core.domain.model.AllData
import submission.dicoding.jetpack.mymovie.core.domain.model.FavoriteData
import submission.dicoding.jetpack.mymovie.core.util.DataMapper
import submission.dicoding.jetpack.mymovie.core.util.Function.createToastNetworkError
import submission.dicoding.jetpack.mymovie.core.util.Function.glide
import submission.dicoding.jetpack.mymovie.core.util.Status
import submission.dicoding.jetpack.mymovie.databinding.FragmentDetailBinding

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding
    private val args by navArgs<DetailFragmentArgs>()
    private val viewModel: DetailViewModel by hiltNavGraphViewModels(R.id.nav_host)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

        binding?.apply {
            layoutRefresh.setOnRefreshListener {
                subscribeToObserver()
            }
            ibRefresh.setOnClickListener {
                subscribeToObserver()
            }
        }
        subscribeToObserver()
    }

    private fun subscribeToObserver() {
        val path = args.path
        val layoutRefresh = binding?.layoutRefresh
        val ibRefresh = binding?.ibRefresh

        path.let {
            viewModel.getDetailItem(it[0], it[1].toInt()).observe(viewLifecycleOwner, { response ->
                with(response) {
                    layoutRefresh?.isRefreshing = status == Status.LOADING

                    when (status) {
                        Status.SUCCESS -> {
                            data?.let { allData ->
                                setupUI(allData)
                                setupToDatabase(DataMapper.allDataToFavorite(allData))
                            }
                        }
                        Status.ERROR -> {
                            ibRefresh?.isVisible = true
                            createToastNetworkError(true, requireContext())
                        }
                        Status.LOADING -> {
                            ibRefresh?.isVisible = false
                        }
                    }
                }
            })
        }
    }


    private fun setupToDatabase(favoriteData: FavoriteData) {
        binding?.apply {
            tbFavorite.setOnClickListener {
                tbFavorite.isChecked != tbFavorite.isChecked
                if (tbFavorite.isChecked) {
                    viewModel.insertFavorite(favoriteData)
                } else {
                    viewModel.deleteFavorite(favoriteData)
                }
                createSnackBar(layoutContainer, tbFavorite.isChecked, favoriteData.title)
            }
        }
    }

    private fun createSnackBar(view: View, isSaved: Boolean, title: String) {
        if (isSaved) Snackbar.make(view, "$title saved", 500).show()
        else Snackbar.make(view, "$title unsaved", 500).show()
    }

    private fun setupUI(allData: AllData) {
        binding?.apply {
            with(allData) {
                requireView().glide(poster_path, ivPoster)
                tvTitle.text = title
                tvOverview.text = overview
                tvDate.text = date

                viewModel.isFavorite(id).observe(viewLifecycleOwner, { count ->
                    tbFavorite.isChecked = count != 0
                })
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}