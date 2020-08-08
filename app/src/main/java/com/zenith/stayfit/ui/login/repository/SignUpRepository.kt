package com.zenith.stayfit.ui.login.repository


import com.zenith.stayfit.commons.ServerConstants
import com.zenith.stayfit.commons.getMessageFromResponseCode
import com.zenith.stayfit.ui.login.model.register.RegisterBody
import com.zenith.stayfit.ui.login.network.AuthenticationService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
        val messageFromServer = withContext(Dispatchers.IO) {
            val response = authenticationService.registerUser(
                registerBody
            )
            if (response.isSuccessful) {
                return@withContext getMessageFromResponseCode(response.code(),response.body()!!.data.message)
            }else {
                return@withContext getMessageFromResponseCode(response.code(),response.body()!!.data.message)
            }
        }
        message = messageFromServer
        return message
    }
}