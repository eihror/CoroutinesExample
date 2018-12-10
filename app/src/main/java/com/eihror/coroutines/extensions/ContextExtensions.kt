package com.eihror.coroutines.extensions

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast

fun Context.toast(m: String) {
    Toast.makeText(this, m, Toast.LENGTH_SHORT).show()
}

fun Context.getAct(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) {
            return context
        }
        context = context.baseContext
    }
    return null
}