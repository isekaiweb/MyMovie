package submission.dicoding.jetpack.mymovie.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import submission.dicoding.jetpack.mymovie.R
import submission.dicoding.jetpack.mymovie.databinding.FragmentMainBinding
import submission.dicoding.jetpack.mymovie.core.ui.adapters.ViewPagerListAdapter

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding
    private lateinit var pagerListAdapter: ViewPagerListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        setupTabLayout()
        setupNavigateToAnotherFragment()
    }


    private fun setupTabLayout() {
        val tabTitle = listOf("Movies  \uD83C\uDFAC", "Series  \uD83D\uDCFA")
        pagerListAdapter = ViewPagerListAdapter(requireActivity() as AppCompatActivity)
        binding?.apply {
            viewpager.adapter = pagerListAdapter
            TabLayoutMediator(tab, viewpager) { tab, pos ->
                tab.text = tabTitle[pos]
            }.attach()
        }
    }

    private fun setupNavigateToAnotherFragment() {
        binding?.apply {
            ibBookmark.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToFavoriteFragment())
            }
            ibSearch.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToSearchFragment())
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}