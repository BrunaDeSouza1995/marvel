package com.poc.bruna.marvel.feature.search.business.interactor

import com.poc.bruna.marvel.plugin.model.response.CharacterComicsResponse
import com.poc.bruna.marvel.plugin.model.response.CharacterResponse

interface SearchRepository {
    suspend fun getCharacters(name: String): CharacterResponse
    suspend fun getComics(url: String): CharacterComicsResponse
}