package com.example.bestapptesttask.presentation.extensions

import android.app.AlertDialog
import android.content.Context
import com.example.bestapptesttask.utils.Constants.CANCEL
import com.example.bestapptesttask.utils.Constants.TRY_AGAIN

fun Context.showErrorAlertDialog(title: String, message: String, onOKClick: () -> Unit) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setCancelable(false)
        .setPositiveButton(TRY_AGAIN) { _, _ -> onOKClick() }
        .setNegativeButton(CANCEL) { dialog, _ -> dialog.cancel() }
        .show()

}