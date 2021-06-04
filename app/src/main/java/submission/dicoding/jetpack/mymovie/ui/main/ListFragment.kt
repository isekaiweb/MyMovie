package submission.dicoding.jetpack.mymovie.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import submission.dicoding.jetpack.mymovie.R
import submission.dicoding.jetpack.mymovie.core.adapters.ListAdapter
import submission.dicoding.jetpack.mymovie.core.adapters.MovieLoadStateAdapter
import submission.dicoding.jetpack.mymovie.core.util.Function.createToastNetworkError
import submission.dicoding.jetpack.mymovie.databinding.FragmentListBinding


@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding
    private val viewModel: ListViewModel by viewModels()
    private lateinit var listAdapter: ListAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)

        setupAdapter()
        setData()
        setupUI()
        setupMoveToDetail()
    }

    private fun setupAdapter() {
        listAdapter = ListAdapter()
        binding?.rvList?.apply {
            adapter = listAdapter.withLoadStateFooter(
                MovieLoadStateAdapter { listAdapter.retry() }
            )
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    private fun setupUI() {
        binding?.apply {
            listAdapter.addLoadStateListener { state ->
                rvList.isVisible = state.source.refresh is LoadState.NotLoading
                createToastNetworkError(state.source.refresh is LoadState.Error, requireContext())
                ibRefresh.isVisible = state.source.refresh is LoadState.Error
                layoutRefresh.isRefreshing =
                    state.source.refresh is LoadState.Loading
            }

            layoutRefresh.setColorSchemeColors(
                ContextCompat.getColor(requireContext(), R.color.black)
            )

            layoutRefresh.setOnRefreshListener {
                listAdapter.refresh()
            }

            ibRefresh.setOnClickListener {
                listAdapter.retry()
            }
        }
    }


    private fun setData() {
        val pos = arguments?.getInt(PAGE_TYPE)
        val mediaType = requireActivity().resources.getStringArray(R.array.media_type)
        val category = requireActivity().resources.getStringArray(R.array.category)

        if (pos != null) {
            subscribeToViewModel(mediaType[pos], category[pos])
        }
    }

    private fun subscribeToViewModel(mediaType: String, category: String) {
        viewModel.getAllList(mediaType, category).observe(viewLifecycleOwner, {
            listAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        })
    }


    private fun setupMoveToDetail() {
        listAdapter.setOnItemClickListener { allData ->
            val bundle = arrayOf(allData.media_type, allData.id.toString())
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToDetailFragment(
                    bundle
                )
            )
        }
    }


    override fun onDestroyView() {
        binding?.root?.removeAllViews()
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val PAGE_TYPE = "PAGE_TYPE"
    }
}