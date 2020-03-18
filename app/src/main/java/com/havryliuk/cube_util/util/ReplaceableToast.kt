package com.havryliuk.cube_util.util

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.havryliuk.cube_util.util.ReplaceableToast.toast

/**
 * This extension fun set toast text if it is already showed, else set text and show.
 * Toast will be showed in context that invoked this fun.
 */
@SuppressLint("ShowToast")
fun Context.replaceableToast(messageId: Int, duration: Int = Toast.LENGTH_SHORT) {
    if (toast == null) {
        toast = Toast.makeText(this, messageId, duration)
    }

    if (toast?.view?.isShown == false) {
        toast?.setText(messageId)
        toast?.show()
    } else {
        toast?.setText(messageId)
    }
}

/**
 * String [message] in args, same as [Context.replaceableToast]
 */
@SuppressLint("ShowToast")
fun Context.replaceableToast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    if (toast == null) {
        toast = Toast.makeText(this, message, duration)
    }

    if (toast?.view?.isShown == false) {
        toast?.setText(message)
        toast?.show()
    } else {
        toast?.setText(message)
    }
}

/**
 * Object to wrap [toast] that used in [Context.replaceableToast] function.
 */
object ReplaceableToast {
    var toast: Toast? = null
}