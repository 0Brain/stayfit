package com.zenith.stayfit.ui.diary.network

import com.zenith.stayfit.ui.diary.model.Food
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodService {
    @GET("ingredient/")
    fun getFoods(
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0
    ): Call<Food>
}