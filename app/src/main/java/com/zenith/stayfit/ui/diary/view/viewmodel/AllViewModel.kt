package com.zenith.stayfit.ui.diary.view.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.zenith.stayfit.ui.diary.model.Result
import com.zenith.stayfit.ui.diary.repository.AllRepository
import kotlinx.coroutines.flow.Flow

class AllViewModel @ViewModelInject constructor(
    private val allRepository: AllRepository
) : ViewModel() {

    suspend fun getFoodItems(): Flow<List<Result>> {
        return allRepository.getAllFoods()
    }
}