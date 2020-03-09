package com.poc.bruna.marvel.plugin.dagger.module

import com.poc.bruna.marvel.feature.search.business.interactor.SearchRepository
import com.poc.bruna.marvel.plugin.repository.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSearchRepository(repositoryInjector: SearchRepositoryImpl): SearchRepository
}
