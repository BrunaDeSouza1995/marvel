package com.poc.bruna.marvel.plugin.repository

import com.poc.bruna.marvel.feature.search.business.interactor.SearchRepository
import com.poc.bruna.marvel.plugin.model.response.CharacterComicsResponse
import com.poc.bruna.marvel.plugin.model.response.CharacterResponse
import com.poc.bruna.marvel.plugin.retrofit.MarvelService
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val service: MarvelService) :
    SearchRepository {

    override suspend fun getCharacters(name: String): CharacterResponse {
        return service.getCharacters(name = name)
    }

    override suspend fun getComics(url: String): CharacterComicsResponse {
        return service.getComics(url)
    }
}