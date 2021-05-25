package submission.dicoding.jetpack.mymovie.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import submission.dicoding.jetpack.mymovie.R
import submission.dicoding.jetpack.mymovie.core.adapters.FavoriteAdapter
import submission.dicoding.jetpack.mymovie.databinding.FragmentListFavoriteBinding
import submission.dicoding.jetpack.mymovie.ui.main.ListFragment
import submission.dicoding.jetpack.mymovie.util.Function.isPortrait
import javax.inject.Inject

@AndroidEntryPoint
class ListFavoriteFragment : Fragment(R.layout.fragment_list_favorite) {
    private var _binding: FragmentListFavoriteBinding? = null
    private val binding get() = _binding
    private val viewModel: FavoriteViewModel by hiltNavGraphViewModels(R.id.nav_host)

    @Inject
    lateinit var favoriteAdapter: FavoriteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListFavoriteBinding.bind(view)

        setupAdapter()
        setData()
        setupMoveToDetail()
    }


    private fun setupAdapter() {
        binding?.rvFavorite?.apply {
            adapter = favoriteAdapter
            layoutManager = GridLayoutManager(context, getSpansCount())
        }
    }

    private fun setData() {
        val pos = arguments?.getInt(ListFragment.PAGE_TYPE)
        val mediaType = requireActivity().resources.getStringArray(R.array.media_type)

        if (pos != null) {
            subscribeToViewModel(mediaType[pos])
            handleEmptyData(mediaType[pos])
        }
    }

    private fun handleEmptyData(mediaType: String) {
        viewModel.getSumOfAllFavorite(mediaType).observe(viewLifecycleOwner, { total ->
            binding?.ivEmpty?.isVisible = total < 1
        })
    }


    private fun getSpansCount() = if (isPortrait()) 2 else 4


    private fun subscribeToViewModel(mediaType: String) {
        viewModel.getAllMovie(mediaType).observe(viewLifecycleOwner, {
            favoriteAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        })
    }

    private fun setupMoveToDetail() {
        favoriteAdapter.setOnItemClickListener { movie ->
            val bundle = arrayOf(movie.media_type, movie.id.toString())
            findNavController().navigate(
                FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(
                    bundle
                )
            )
        }
    }

    companion object {
        const val PAGE_TYPE = "PAGE_TYPE"
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}