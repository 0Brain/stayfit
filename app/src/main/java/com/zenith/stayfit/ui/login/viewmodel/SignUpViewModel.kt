
package com.zenith.stayfit.ui.login.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenith.stayfit.commons.ServerConstants
import com.zenith.stayfit.commons.validateFields
import com.zenith.stayfit.ui.login.repository.SignUpRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class SignUpViewModel @ViewModelInject constructor(
  private val signUpRepository: SignUpRepository
) : ViewModel() {

    var email: String? = null
    var username: String? = null
    var phone: String? = null
    var password: String? = null
    var confirmPassword: String? = null

    private var exceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, exception ->
            Timber.d(exception)
        }

    fun signUpUser() {
        if (validateFields(password, confirmPassword, email, username, phone)) {
            viewModelScope.launch(exceptionHandler) {
                getServerMessage().let { apiResponse ->
                    Timber.d(apiResponse)
                    if (apiResponse == ServerConstants.successfulSignUpResponse) {
                        Timber.d("cleared")
                    }
                }
            }
        }
    }

    private suspend fun getServerMessage(): String = withContext(Dispatchers.IO) {
        signUpRepository.signUp(username!!, email!!, phone!!.toLong(), password!!)
    }
}
