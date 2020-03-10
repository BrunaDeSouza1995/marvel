package com.poc.bruna.marvel.feature.search.gateway

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.poc.bruna.marvel.feature.base.business.data.Result
import com.poc.bruna.marvel.feature.base.gateway.Event
import com.poc.bruna.marvel.feature.search.business.data.CharacterData
import com.poc.bruna.marvel.feature.search.business.interactor.SearchUseCase
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase) :
    ViewModel() {

    val charactersLiveData = MutableLiveData<Result<CharacterData>>()

    val searchLiveData: LiveData<Event<Result<CharacterData>>> =
        Transformations.map(charactersLiveData) {
            Event(it)
        }

    fun searchByName(name: String) {
        searchUseCase(name.trim(), charactersLiveData)
    }
}