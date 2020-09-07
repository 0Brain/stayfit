package com.zenith.stayfit.ui.diary.repository

import androidx.lifecycle.LiveData
import com.zenith.stayfit.ui.diary.model.Result
import com.zenith.stayfit.ui.diary.network.FoodService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class AllRepository @Inject constructor(private val foodService: FoodService) {

    suspend fun getAllFoods(): Flow<List<Result>> = flow {
        val response = foodService.getFoods()
        if (response.isSuccessful) {
            emit(response.body()!!.results)
            Timber.d("Thread in viewmodel = ${Thread.currentThread().name}")
        } else if (!response.isSuccessful) {
            Timber.d(response.message())
        }
    }.flowOn(Dispatchers.IO)
}