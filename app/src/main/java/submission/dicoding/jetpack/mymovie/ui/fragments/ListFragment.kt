package submission.dicoding.jetpack.mymovie.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import submission.dicoding.jetpack.mymovie.R
import submission.dicoding.jetpack.mymovie.databinding.FragmentListBinding
import submission.dicoding.jetpack.mymovie.ui.adapters.ListAdapter
import submission.dicoding.jetpack.mymovie.ui.viewmodels.ListViewModel
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!


    @Inject
    lateinit var viewModel: ListViewModel

    @Inject
    lateinit var listAdapter: ListAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)

        binding.rvList.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        setData()
        subscribeToObservers()
        setupMoveToDetail()
    }


    private fun setData() {
        arguments?.getInt(PAGE_TYPE)?.let {
            viewModel.setData(it)
        }

    }

    private fun subscribeToObservers() {
        viewModel.currTab.observe(viewLifecycleOwner, { movie ->
            listAdapter.movie = movie
        })
    }


    private fun setupMoveToDetail() {
        listAdapter.setOnItemClickListener { movie ->
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToDetailFragment(
                    movie.id
                )
            )
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        const val PAGE_TYPE = "PAGE_TYPE"
    }
}