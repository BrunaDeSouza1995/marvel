package com.poc.bruna.marvel.plugin.retrofit

import com.poc.bruna.marvel.BuildConfig.MARVEL_HASH
import com.poc.bruna.marvel.BuildConfig.MARVEL_PUBLIC_KEY
import com.poc.bruna.marvel.BuildConfig.MARVEL_TS
import com.poc.bruna.marvel.plugin.model.response.CharacterComicsResponse
import com.poc.bruna.marvel.plugin.model.response.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface MarvelService {

    @GET("/v1/public/characters?")
    suspend fun getCharacters(
        @Query("ts") ts: String = MARVEL_TS,
        @Query("apikey") apiKey: String = MARVEL_PUBLIC_KEY,
        @Query("hash") hash: String = MARVEL_HASH,
        @Query("name") name: String
    ): CharacterResponse

    @GET
    suspend fun getComics(
        @Url url: String,
        @Query("ts") ts: String = MARVEL_TS,
        @Query("apikey") apiKey: String = MARVEL_PUBLIC_KEY,
        @Query("hash") hash: String = MARVEL_HASH
    ): CharacterComicsResponse
}
