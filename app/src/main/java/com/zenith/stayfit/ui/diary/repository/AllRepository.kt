
package com.zenith.stayfit.ui.diary.repository

import com.zenith.stayfit.ui.diary.model.Result
import com.zenith.stayfit.ui.diary.network.FoodService
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

class AllRepository @Inject constructor(private val foodService: FoodService) {

    suspend fun getAllFoods(): Flow<List<Result>> = flow {
        val response = foodService.getFoods()
        if (response.isSuccessful) {
            emit(response.body()!!.results)
            if(response.raw().networkResponse != null){
                Timber.d( "onResponse: response is from NETWORK...");
            }
            else if(response.raw().cacheResponse != null
                && response.raw().networkResponse == null){
                Timber.d("onResponse: response is from CACHE...");
            }
        }
    }.catch { cause ->
        Timber.d(cause)
    }.flowOn(Dispatchers.IO)
}
