package submission.dicoding.jetpack.mymovie.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import submission.dicoding.jetpack.mymovie.favorite.R
import submission.dicoding.jetpack.mymovie.favorite.databinding.FragmentFavoriteBinding
import submission.dicoding.jetpack.mymovie.favorite.ui.ViewPagerFavoriteAdapter

@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite) {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding
    private lateinit var pagerListAdapter: ViewPagerFavoriteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoriteBinding.bind(view)

        setupTabLayout()
        setupNavigateBack()
    }

    private fun setupTabLayout() {
        val tabTitle = listOf("Movies  \uD83C\uDFAC", "Series  \uD83D\uDCFA")
        pagerListAdapter =
            ViewPagerFavoriteAdapter(requireActivity() as AppCompatActivity)
        binding?.apply {
            viewpager.adapter = pagerListAdapter
            TabLayoutMediator(tab, viewpager) { tab, pos ->
                tab.text = tabTitle[pos]
            }.attach()
        }
    }


    private fun setupNavigateBack() {
        binding?.ibBack?.setOnClickListener {
            findNavController().popBackStack()
        }
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}