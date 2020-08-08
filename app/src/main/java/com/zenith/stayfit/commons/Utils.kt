package com.zenith.stayfit.commons

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import dmax.dialog.SpotsDialog

fun checkEmail(email: String): Boolean {
    return Constants.EMAIL_ADDRESS_PATTERN.matcher(email).matches()
}

fun clearForm(group: ViewGroup) {
    var i = 0
    val count = group.childCount
    while (i < count) {
        val view = group.getChildAt(i)
        if (view is EditText) {
            view.setText("")
        }
        if (view is ViewGroup && view.childCount > 0)
            clearForm(view)
        ++i
    }
}


fun getProgressDialog(context: Context, msg: String): AlertDialog {

    return SpotsDialog.Builder()
        .setContext(context)
        .setMessage(msg)
        .setCancelable(false)
        .build()
        .apply {
            //show()
        }
}

fun getMessageFromResponseCode(statusCode: Int,statusResponse:String): String {
    return when (statusCode) {
        statusCode -> statusResponse
//        200 -> ServerConstants.successfulSignUpResponse
//        500 -> ServerConstants.duplicateUser
//        501 -> "The server either does not recognize the request method"
//        502 -> "Bad Gateway"
//        503 -> "Service Unavailable"
//        504 -> "Gateway Timeout"
//        505 -> "HTTP Version Not Supported"
//        511 -> "Network Authentication Required"

        else -> "Something wrong happened"
    }
}

fun View.showSnackbar(snackbarText: String, timeLength: Int) {
    Snackbar.make(this, snackbarText, timeLength).run {
        addCallback(object : Snackbar.Callback() {
            override fun onShown(sb: Snackbar?) {
            }

            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
            }
        })
        show()
    }
}


