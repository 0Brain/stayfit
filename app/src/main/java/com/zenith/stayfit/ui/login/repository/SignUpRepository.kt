package com.zenith.stayfit.ui.login.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zenith.stayfit.ui.login.model.register.RegisterBody
import com.zenith.stayfit.ui.login.network.AuthenticationService
import kotlinx.coroutines.*
import timber.log.Timber

import javax.inject.Inject

class SignUpRepository @Inject constructor(private val authenticationService: AuthenticationService) {


    suspend fun signUp(
        username: String,
        email: String,
        phone: Long,
        password: String
    ): String {
        val message:String
        val registerBody = RegisterBody(
            email,
            username,
            password,
            phone
        )
            val messageFromServer = CoroutineScope(Dispatchers.Default).async{
                Timber.d("Repository2 ${Thread.currentThread().name}")
                val response = authenticationService.registerUser(
                    registerBody
                )
                if (response.isSuccessful) {
                    return@async when (response.body()!!.code.toInt()) {
                        200 -> {
                            "Sign-Up successful"
                        }
                        449 -> {
                            "Something went wrong"
                        }
                        500 -> {
                            "User Already Exists"
                        }
                        else -> response.message()
                    }
                } else {
                    return@async response.message()

                }
            }
            message = messageFromServer.await()
        return message
    }
}