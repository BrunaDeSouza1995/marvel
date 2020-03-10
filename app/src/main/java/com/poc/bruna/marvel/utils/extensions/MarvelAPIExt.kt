package com.poc.bruna.marvel.utils.extensions

import com.poc.bruna.marvel.plugin.model.response.CharacterResponse

fun CharacterResponse.getComicsURI(): String {
    return data.results.first().comics.collectionURI
}
