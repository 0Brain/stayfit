package com.zenith.stayfit.ui.diary.network

import com.zenith.stayfit.ui.diary.model.Food
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodService {
    @GET("ingredient/")
    suspend fun getFoods(
        @Query("limit") limit: Int = 9231,
        @Query("offset") offset: Int = 0
    ): Response<Food>
}