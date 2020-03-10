package com.poc.bruna.marvel.feature.base.view

import android.content.Context
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    open val idToShowInfoBar: View? = null

    fun showError(@StringRes error: Int) {
        idToShowInfoBar?.let { Snackbar.make(it, error, Snackbar.LENGTH_LONG).show() }
    }
}