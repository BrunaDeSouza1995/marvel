package com.poc.bruna.marvel.plugin.dagger.application

import android.app.Application
import com.poc.bruna.marvel.feature.application.MarvelApplication
import com.poc.bruna.marvel.plugin.dagger.module.AppModule
import com.poc.bruna.marvel.plugin.dagger.module.FragmentModule
import com.poc.bruna.marvel.plugin.dagger.module.RepositoryModule
import com.poc.bruna.marvel.plugin.dagger.module.RetrofitModule
import com.poc.bruna.marvel.plugin.dagger.viewmodel.ViewModelFactoryModule
import com.poc.bruna.marvel.plugin.dagger.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        RetrofitModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        FragmentModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: MarvelApplication)
}