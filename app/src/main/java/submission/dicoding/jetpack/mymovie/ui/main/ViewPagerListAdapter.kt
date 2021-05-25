package submission.dicoding.jetpack.mymovie.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerListAdapter constructor(
    activity: AppCompatActivity,
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(pos: Int): Fragment {
        val fragment = ListFragment()
        fragment.arguments = Bundle().apply {
            putInt(ListFragment.PAGE_TYPE, pos)
        }

        return fragment
    }


}