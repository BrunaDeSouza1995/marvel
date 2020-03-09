package com.poc.bruna.marvel.utils.extensions

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.poc.bruna.marvel.R

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide(keepInLayout: Boolean = false) {
    visibility = if (keepInLayout) View.INVISIBLE else View.GONE
}

fun ImageView.loadImage(image: String){
    Glide.with(this)
        .load(image)
        .placeholder(R.drawable.ic_marvel)
        .error(R.drawable.ic_marvel)
        .into(this)
}
