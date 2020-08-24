package com.zenith.stayfit.ui.meals.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.zenith.stayfit.R
import com.zenith.stayfit.databinding.FragmentMealsBinding
import com.zenith.stayfit.ui.meals.datasource.Meals
import com.zenith.stayfit.ui.meals.view.adapters.MealTypeAdapter


class MealsFragment : Fragment(R.layout.fragment_meals) {

    private lateinit var fragmentMealsBinding: FragmentMealsBinding
    private lateinit var mealsLayoutManager: GridLayoutManager

    private val mealsAdapter: MealTypeAdapter by lazy {
        MealTypeAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentMealsBinding = FragmentMealsBinding.bind(view)
        mealsLayoutManager = GridLayoutManager(requireContext(),3)
        fragmentMealsBinding.rvMeals.layoutManager = mealsLayoutManager
        val meals = Meals.getMeals()
        mealsAdapter.insertTo(meals)
        fragmentMealsBinding.rvMeals.adapter = mealsAdapter
        mealsAdapter.onItemClick = {
            Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
        }
    }
}