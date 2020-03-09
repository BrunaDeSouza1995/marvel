package com.poc.bruna.marvel.plugin.dagger.module

import com.poc.bruna.marvel.feature.character.view.CharacterFragment
import com.poc.bruna.marvel.feature.search.view.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun contributeCharacterFragment(): CharacterFragment
}