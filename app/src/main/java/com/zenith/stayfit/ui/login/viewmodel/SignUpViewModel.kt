package com.zenith.stayfit.ui.login.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.zenith.stayfit.ui.login.repository.SignUpRepository

class SignUpViewModel @ViewModelInject constructor(
    private val signUpRepository: SignUpRepository
) : ViewModel() {




    suspend fun signUpEntries(
        username: String,
        email: String,
        phone: Long,
        password: String
    ):String {
        return signUpRepository.signUp(username, email, phone, password)
    }
}