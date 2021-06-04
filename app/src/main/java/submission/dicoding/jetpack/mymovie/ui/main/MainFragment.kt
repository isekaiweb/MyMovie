package submission.dicoding.jetpack.mymovie.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import dagger.hilt.android.AndroidEntryPoint
import submission.dicoding.jetpack.mymovie.R
import submission.dicoding.jetpack.mymovie.databinding.FragmentMainBinding
import timber.log.Timber


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
                try {
                    installFavModule()
                } catch (e: Exception) {
                    Toast.makeText(activity, "Module not found", Toast.LENGTH_SHORT).show()
                }
            }
            ibSearch.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToSearchFragment())
            }
        }
    }

    private fun installFavModule() {
        val splitInstallManager = SplitInstallManagerFactory.create(requireContext())
        val module = "favorite"
        if (splitInstallManager.installedModules.contains(module)) {
            val action = MainFragmentDirections.actionMainFragmentToFavoriteFragment()
            findNavController().navigate(action)
        } else {
            val request = SplitInstallRequest.newBuilder()
                .addModule(module)
                .build()
            splitInstallManager.startInstall(request)
                .addOnSuccessListener {
                    Toast.makeText(activity, "Success installing module", Toast.LENGTH_SHORT).show()
                    val uri = Uri.parse("submission://favorite")
                    startActivity(Intent(Intent.ACTION_VIEW, uri))
                }
                .addOnFailureListener {
                    Toast.makeText(activity, "Error installing module", Toast.LENGTH_SHORT).show()
                    Timber.e(it)
                }
        }
    }

    override fun onDestroyView() {
        binding?.root?.removeAllViews()
        _binding = null
        super.onDestroyView()
    }
}