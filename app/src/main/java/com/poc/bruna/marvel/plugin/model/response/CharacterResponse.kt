package com.poc.bruna.marvel.plugin.model.response

data class CharacterResponse(
    val code: String,
    val status: String,
    val copyright: String,
    val attributionText: String,
    val attributionHTML: String,
    val `data`: Data,
    val etag: String
) {
    data class Data(
        val offset: String,
        val limit: String,
        val total: String,
        val count: Int,
        val results: List<Result>
    ) {
        data class Result(
            val id: String,
            val name: String,
            val description: String,
            val modified: String,
            val resourceURI: String,
            val urls: List<Url>,
            val thumbnail: Thumbnail,
            val comics: Comics,
            val stories: Stories,
            val events: Events,
            val series: Series
        ) {
            data class Url(
                val type: String,
                val url: String
            )

            data class Thumbnail(
                val path: String,
                val extension: String
            ) {
                fun getStandardFantastic(): String {
                    return "$path/standard_fantastic.$extension"
                }
            }

            data class Comics(
                val available: String,
                val returned: String,
                val collectionURI: String,
                val items: List<Item>
            ) {
                data class Item(
                    val resourceURI: String,
                    val name: String
                )
            }

            data class Stories(
                val available: String,
                val returned: String,
                val collectionURI: String,
                val items: List<Item>
            ) {
                data class Item(
                    val resourceURI: String,
                    val name: String,
                    val type: String
                )
            }

            data class Events(
                val available: String,
                val returned: String,
                val collectionURI: String,
                val items: List<Item>
            ) {
                data class Item(
                    val resourceURI: String,
                    val name: String
                )
            }

            data class Series(
                val available: String,
                val returned: String,
                val collectionURI: String,
                val items: List<Item>
            ) {
                data class Item(
                    val resourceURI: String,
                    val name: String
                )
            }
        }
    }
}