package com.zenith.stayfit.commons

import android.app.AlertDialog
import android.content.Context
import dmax.dialog.SpotsDialog
import java.util.regex.Pattern

object Constants {

    const val SIGN_IN:String = "/api/v1/login"

    const val SIGN_UP: String = "/api/v1/register"

    const val GET_USERS = "/api/v1/users"

    const val GET_USER = "/api/v1//user/"

    const val BASE_URL: String = "http://10.0.2.2:5000"

    val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
}