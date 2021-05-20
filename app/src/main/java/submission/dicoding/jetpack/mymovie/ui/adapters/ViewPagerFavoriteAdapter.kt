package submission.dicoding.jetpack.mymovie.ui.adapters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import submission.dicoding.jetpack.mymovie.ui.fragments.ListFavoriteFragment


class ViewPagerFavoriteAdapter constructor(
    activity: AppCompatActivity,
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(pos: Int): Fragment {
        val fragment = ListFavoriteFragment()
        fragment.arguments = Bundle().apply {
            putInt(ListFavoriteFragment.PAGE_TYPE, pos)
        }

        return fragment
    }


}