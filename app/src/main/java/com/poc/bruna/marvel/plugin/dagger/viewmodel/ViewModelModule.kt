package com.poc.bruna.marvel.plugin.dagger.viewmodel

import androidx.lifecycle.ViewModel

import br.com.ifood.rider.plugin.dagger.viewmodel.ViewModelKey
import com.poc.bruna.marvel.feature.search.gateway.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModel): ViewModel
}
