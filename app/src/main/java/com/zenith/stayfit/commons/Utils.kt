
package com.zenith.stayfit.commons

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import dmax.dialog.SpotsDialog
import timber.log.Timber

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
            // show()
        }
}

fun getMessageFromResponseCode(statusCode: Int, statusResponse: String): String {
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
fun validateFields(password: String?, confirmPassword: String?, email: String?, username: String?, phone: String?): Boolean {
    if (password.isNullOrBlank() || confirmPassword.isNullOrBlank() || email.isNullOrBlank() || username.isNullOrBlank() || phone.isNullOrBlank()) {
        Timber.d("Please fill all fields")
        // Snackbar.make(binding.root, , Snackbar.LENGTH_SHORT).show()
        return false
    }
    if (!checkEmail(email.toString())) {
        Timber.d("Invalid Email")
        // Snackbar.make(binding.root, "Invalid Email", Snackbar.LENGTH_SHORT).show()
        return false
    }
    if (password != confirmPassword) {
        Timber.d("Passwords do not match")
        // Snackbar.make(binding.root, "Passwords do not match", Snackbar.LENGTH_SHORT).show()
        return false
    }
    return true
}
