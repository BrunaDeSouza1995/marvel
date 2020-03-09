package com.poc.bruna.marvel.utils.extensions

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.poc.bruna.marvel.R

val navOptionsRight: NavOptions.Builder
    get() = NavOptions
        .Builder()
        .setEnterAnim(R.anim.navigation_pop_enter_slide_right)
        .setExitAnim(R.anim.navigation_pop_exit_slide_right)
        .setPopEnterAnim(R.anim.navigation_enter_slide_left)
        .setPopExitAnim(R.anim.navigation_exit_slide_left)

fun Fragment?.navigate(@IdRes action: Int, navOptions: NavOptions? = navOptionsRight.build()) {
    this?.findNavController()?.navigate(action, null, navOptions)
}