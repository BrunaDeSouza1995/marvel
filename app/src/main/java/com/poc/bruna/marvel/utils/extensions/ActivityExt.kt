package com.poc.bruna.marvel.utils.extensions

import android.app.Activity
import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

private const val FLAGS: Int = 0

fun Activity?.hideKeyboard() {
    val view = this?.currentFocus
    when {
        view != null -> (this?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .apply { hideSoftInputFromWindow(view.windowToken, FLAGS) }
        else -> this?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }
}