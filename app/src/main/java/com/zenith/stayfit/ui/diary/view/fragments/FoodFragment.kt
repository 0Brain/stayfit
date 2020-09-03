package com.zenith.stayfit.ui.diary.view.fragments

import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.zenith.stayfit.R
import com.zenith.stayfit.databinding.FragmentAddFoodBinding


class FoodFragment : Fragment(R.layout.fragment_add_food) {

    private lateinit var fragmentFoodBinding: FragmentAddFoodBinding
    private lateinit var tabTitles: Array<String>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentFoodBinding = FragmentAddFoodBinding.bind(view)
        tabTitles = resources.getStringArray(R.array.tab_items)

        fragmentFoodBinding.vpAddFood.apply {
            orientation = ORIENTATION_HORIZONTAL
            adapter = FoodsViewPagerAdapter(this@FoodFragment)
        }
        TabLayoutMediator(
            fragmentFoodBinding.tlAddFood,
            fragmentFoodBinding.vpAddFood,
            TabConfigurationStrategy { tab: TabLayout.Tab, position: Int ->
                tab.text = tabTitles[position]
            }).attach()
    }

    inner class FoodsViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        @NonNull
        override fun getItemCount(): Int {
            return tabTitles.size
        }

        @NonNull
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> AllFragment()
                1 -> RecipesFragment()
                else -> AllFragment()
            }
        }
    }
}