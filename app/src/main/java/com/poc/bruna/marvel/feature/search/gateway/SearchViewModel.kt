package com.poc.bruna.marvel.feature.search.gateway

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.poc.bruna.marvel.feature.base.business.interactor.Result
import com.poc.bruna.marvel.feature.base.gateway.Event
import com.poc.bruna.marvel.feature.search.business.data.CharacterData
import com.poc.bruna.marvel.feature.search.business.interactor.SearchUseCase
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase) :
    ViewModel() {

    private val _errorLiveData = MutableLiveData<Result.Error>()
    val charactersLiveData = MutableLiveData<Result<CharacterData>>()

    val navigateToCharacter: LiveData<Event<Unit>?> = Transformations.map(charactersLiveData) {
        if (it is Result.Success) Event(Unit) else null
    }

    val errorLiveData: LiveData<Event<Result.Error>?> = Transformations.map(_errorLiveData) {
        Event(it)
    }

    fun searchByName(name: String) {
        searchUseCase(name.trim(), charactersLiveData, _errorLiveData)
    }
}