package com.poc.bruna.marvel.utils.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified VM : ViewModel> Fragment.activityViewModelProvider(provider: ViewModelProvider.Factory): VM {
    return ViewModelProvider(requireActivity(), provider).get(VM::class.java)
}

inline fun <reified VM : ViewModel> Fragment.viewModelProvider(provider: ViewModelProvider.Factory): VM {
    return ViewModelProvider(this, provider).get(VM::class.java)
}