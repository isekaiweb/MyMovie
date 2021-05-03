package submission.dicoding.jetpack.mymovie.ui.adapters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import submission.dicoding.jetpack.mymovie.other.Constants.KEY_MOVIE
import submission.dicoding.jetpack.mymovie.other.Constants.KEY_TV
import submission.dicoding.jetpack.mymovie.ui.fragments.ListFragment


class ViewPagerAdapter constructor(
    activity: AppCompatActivity,
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(pos: Int): Fragment {
        val fragment = ListFragment()
        val type = when (pos) {
            0 -> KEY_MOVIE
            1 -> KEY_TV
            else -> KEY_MOVIE
        }
        fragment.arguments = Bundle().apply {
            putInt(ListFragment.PAGE_TYPE, type)
        }

        return fragment
    }


}