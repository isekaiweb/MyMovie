package submission.dicoding.jetpack.mymovie.favorite.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.EntryPointAccessors
import submission.dicoding.jetpack.mymovie.core.util.Function.isPortrait
import submission.dicoding.jetpack.mymovie.di.FavoriteModuleDependencies
import submission.dicoding.jetpack.mymovie.favorite.R
import submission.dicoding.jetpack.mymovie.favorite.adapters.FavoriteAdapter
import submission.dicoding.jetpack.mymovie.favorite.databinding.FragmentFavoriteBinding
import submission.dicoding.jetpack.mymovie.favorite.di.DaggerFavoriteComponent
import submission.dicoding.jetpack.mymovie.favorite.util.ViewModelFactory
import javax.inject.Inject

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding
    private lateinit var favoriteAdapter: FavoriteAdapter

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: FavoriteViewModel by viewModels { factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoriteBinding.bind(view)

        setupAdapter()
        subscribeToViewModel()
        handleEmptyData()
        setupNavigateBack()
        setupMoveToDetail()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    private fun setupAdapter() {
        favoriteAdapter = FavoriteAdapter()
        binding?.rvFavorite?.apply {
            adapter = favoriteAdapter
            layoutManager = GridLayoutManager(context, getSpansCount())
        }
    }

    private fun handleEmptyData() {
        viewModel.getSumOfAllFavorite.observe(viewLifecycleOwner, { total ->
            binding?.ivEmpty?.isVisible = total < 1
        })
    }


    private fun getSpansCount() = if (isPortrait()) 2 else 4

    private fun subscribeToViewModel() {
        viewModel.getAllFavorite.observe(viewLifecycleOwner, {
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

    private fun setupNavigateBack() {
        binding?.apply {
            ibBack.setOnClickListener {
                findNavController().popBackStack()
            }
            ivEmpty.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        binding?.root?.removeAllViews()
        _binding = null
        super.onDestroyView()
    }
}