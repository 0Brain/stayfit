package com.zenith.stayfit.commons

import android.app.AlertDialog
import android.content.Context
import android.view.ViewGroup
import android.widget.EditText
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

