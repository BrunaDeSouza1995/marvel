package com.poc.bruna.marvel.plugin.dagger.module

import android.content.Context
import com.poc.bruna.marvel.feature.application.MarvelApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesContext(application: MarvelApplication): Context = application.applicationContext
}