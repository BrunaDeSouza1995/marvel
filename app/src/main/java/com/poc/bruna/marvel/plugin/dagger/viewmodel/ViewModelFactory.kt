package com.poc.bruna.marvel.plugin.dagger.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Provider<ViewModel>? = creators[modelClass]
        if (creator == null) {
            creators.forEach { (key, value) ->
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                }
            }
        }
        if (creator == null) throw IllegalArgumentException("unknown model class $modelClass")
        try {
            return creator?.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
