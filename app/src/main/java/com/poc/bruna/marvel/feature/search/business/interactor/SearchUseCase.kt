package com.poc.bruna.marvel.feature.search.business.interactor

import com.poc.bruna.marvel.feature.base.business.interactor.UseCase
import com.poc.bruna.marvel.feature.search.business.data.CharacterData
import com.poc.bruna.marvel.feature.search.business.data.CharacterData.Companion.convertingByCharacterResponse
import com.poc.bruna.marvel.feature.search.business.expection.NoResultException
import com.poc.bruna.marvel.utils.extensions.getComicsURI
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: SearchRepository) :
    UseCase<String, CharacterData>() {

    override suspend fun execute(param: String?): CharacterData {
        val character = repository.getCharacters(param!!)
        val url = character.getComicsURI()
        val comics = repository.getComics(url)

        if (character.data.count == 0) throw NoResultException()

        return convertingByCharacterResponse(character, comics)
    }
}
