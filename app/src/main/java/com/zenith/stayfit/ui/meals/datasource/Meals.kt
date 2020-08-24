package com.zenith.stayfit.ui.meals.datasource

import com.zenith.stayfit.R
import com.zenith.stayfit.ui.meals.model.MealType

object Meals {

    fun getMeals(): MutableList<MealType> {
        val mealList: MutableList<MealType> = ArrayList()

        val anything = MealType(
            "Anything",
            R.drawable.ic_anything
        )
        mealList.add(anything)

        val paleo = MealType(
            "Paleo",
            R.drawable.ic_paleo
        )
        mealList.add(paleo)

        val vegetarian = MealType(
            "vegetarian",
            R.drawable.ic_vegetarian
        )
        mealList.add(vegetarian)

        val vegan = MealType(
            "vegan",
            R.drawable.ic_vegan
        )
        mealList.add(vegan)

        val ketogenic = MealType(
            "Ketogenic",
            R.drawable.ic_ketogenic
        )
        mealList.add(ketogenic)

        val mediterranean = MealType(
            "Mediterranean",
            R.drawable.ic_mediterranean
        )
        mealList.add(mediterranean)

        return mealList
    }
}