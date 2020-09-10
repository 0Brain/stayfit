
package com.zenith.stayfit.ui.diary.view.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.zenith.stayfit.ui.diary.model.Result
import com.zenith.stayfit.ui.diary.repository.AllRepository
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import timber.log.Timber


class AllViewModel @ViewModelInject constructor(
  private val allRepository: AllRepository
) : ViewModel() {

    suspend fun getFoodItems(): Flow<List<Result>> {
        Timber.d("View model Thread  = ${Thread.currentThread().name}")
        return allRepository.getAllFoods()
    }
}
