package com.poc.bruna.marvel.feature.search.business.data

import com.poc.bruna.marvel.plugin.model.response.CharacterComicsResponse
import com.poc.bruna.marvel.plugin.model.response.CharacterResponse

data class CharacterData(
    val name: String,
    val description: String,
    val imageThumbnail: String,
    val comics: MutableList<Pair<String, String>>
) {
    companion object {
        fun convertingByCharacterResponse(
            characterResponse: CharacterResponse,
            comicsResponse: CharacterComicsResponse
        ): CharacterData {
            val character = characterResponse.data.results.first()
            val name = character.name
            val description = character.description
            val imageThumbnail = character.thumbnail.getStandardFantastic()
            val comics = mutableListOf<Pair<String, String>>()
            comicsResponse.data.results.map { comics.add(it.title to it.thumbnail.getStandardFantastic()) }

            return CharacterData(
                name,
                description,
                imageThumbnail,
                comics
            )
        }
    }
}