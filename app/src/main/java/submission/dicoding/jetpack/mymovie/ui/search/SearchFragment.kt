package submission.dicoding.jetpack.mymovie.ui.search

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import submission.dicoding.jetpack.mymovie.R
import submission.dicoding.jetpack.mymovie.core.adapters.ListAdapter
import submission.dicoding.jetpack.mymovie.core.adapters.MovieLoadStateAdapter
import submission.dicoding.jetpack.mymovie.core.util.Function.createToastNetworkError
import submission.dicoding.jetpack.mymovie.core.util.Function.hideKeyboard
import submission.dicoding.jetpack.mymovie.core.util.Function.setOnPressEnter
import submission.dicoding.jetpack.mymovie.core.util.Function.showKeyboard
import submission.dicoding.jetpack.mymovie.databinding.FragmentSearchBinding
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding
    private val viewModel: SearchViewModel by hiltNavGraphViewModels(R.id.nav_host)
    private lateinit var listAdapter: ListAdapter


    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchBinding.bind(view)

        subscribeToViewModel()
        setupAdapter()
        setupListener()
        setupUI()
        setupMoveToAnotherFragment()
    }


    private fun setupAdapter() {
        listAdapter = ListAdapter()
        binding?.rvSearch?.apply {
            adapter = listAdapter.withLoadStateFooter(
                MovieLoadStateAdapter { listAdapter.retry() }
            )
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupUI() {
        binding?.apply {
            listAdapter.addLoadStateListener { state ->
                rvSearch.isVisible = state.source.refresh is LoadState.NotLoading

                createToastNetworkError(
                    state.source.refresh is LoadState.Error,
                    requireContext()
                )
                ibRefresh.isVisible =
                    state.source.refresh is LoadState.Error
                layoutRefresh.isRefreshing =
                    state.source.refresh is LoadState.Loading

                handleResponseEmpty(state.source.refresh is LoadState.NotLoading)
            }

            ivNotFound.isVisible = false

            layoutRefresh.setColorSchemeColors(
                ContextCompat.getColor(requireContext(), R.color.black)
            )
        }
    }

    private fun handleResponseEmpty(isNotLoading: Boolean) {
        if (isNotLoading) {
            binding?.ivNotFound?.isVisible =
                listAdapter.itemCount == 0 && sharedPreferences.getString("query", null) != null

        }
    }

    private fun setupListener() {
        binding?.apply {
            layoutRefresh.setOnRefreshListener {
                listAdapter.refresh()
            }

            ibRefresh.setOnClickListener {
                listAdapter.retry()
            }

            ivSearch.setOnClickListener {
                subscribeToViewModel()
            }

            setOnPressEnter(etSearch, ivSearch)
        }
    }


    private fun subscribeToViewModel() {

        binding?.etSearch?.apply {
            if (text.toString().isNotEmpty()) {
                sharedPreferences.edit().putString("query", text.toString()).apply()
            }

            val query = sharedPreferences.getString("query", null)

            setOnClickListener {
                requestFocus()
            }

            setOnFocusChangeListener { _, hasFocus ->
                isCursorVisible = hasFocus
            }

            if (query == null) {
                showKeyboard()
                requestFocus()
                binding?.layoutRefresh?.isVisible = false
            } else {

                hideKeyboard()
                clearFocus()
                setText(query)
                viewModel.searchAll(query)
                    .observe(viewLifecycleOwner, {
                        listAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                    })
                binding?.layoutRefresh?.isVisible = true
            }
        }
    }


    private fun setupMoveToAnotherFragment() {
        binding?.apply {
            ibBack.setOnClickListener {
                findNavController().popBackStack()
            }
            listAdapter.setOnItemClickListener {
                val type = it.media_type
                val bundle = arrayOf(type, it.id.toString())

                findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToDetailFragment(
                        bundle
                    )
                )
            }
        }
    }

    override fun onDestroyView() {
        binding?.root?.removeAllViews()
        _binding = null
        super.onDestroyView()
    }
}