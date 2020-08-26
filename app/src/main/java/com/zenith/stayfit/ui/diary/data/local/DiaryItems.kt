
package com.zenith.stayfit.ui.diary.data.local

import com.zenith.stayfit.ui.diary.model.Diary

object DiaryItems {
    fun getItems(): MutableList<Diary> {
        val diaryList: MutableList<Diary> = ArrayList()

        val breakfast = Diary(
            "Breakfast",
            "ADD FOOD"
        )
        diaryList.add(breakfast)

        val lunch = Diary(
            "Lunch",
            "ADD FOOD"
        )
        diaryList.add(lunch)

        val dinner = Diary(
            "Dinner",
            "ADD FOOD"
        )
        diaryList.add(dinner)

        val snacks = Diary(
            "Snacks",
            "ADD FOOD"
        )
        diaryList.add(snacks)

        val exercise = Diary(
            "Exercise",
            "ADD EXERCISE"
        )
        diaryList.add(exercise)

        val water = Diary(
            "Water",
            "ADD WATER"
        )
        diaryList.add(water)

        return diaryList
    }
}
