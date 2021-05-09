package submission.dicoding.jetpack.mymovie.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import submission.dicoding.jetpack.mymovie.R
import submission.dicoding.jetpack.mymovie.databinding.FragmentMainBinding
import submission.dicoding.jetpack.mymovie.ui.adapters.ViewPagerAdapter

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding
    private lateinit var pagerAdapter: ViewPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        setupTabLayout()
    }


    private fun setupTabLayout() {
        val tabIcon = listOf(
            ResourcesCompat.getDrawable(
                requireActivity().resources,
                R.drawable.ic_movie,
                null
            ), ResourcesCompat.getDrawable(requireActivity().resources, R.drawable.ic_tv, null)
        )

        pagerAdapter = ViewPagerAdapter(requireActivity() as AppCompatActivity)
        binding?.apply {
            viewpager.adapter = pagerAdapter
            TabLayoutMediator(tab, viewpager) { tab, pos ->
                tab.icon = tabIcon[pos]
            }.attach()
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}