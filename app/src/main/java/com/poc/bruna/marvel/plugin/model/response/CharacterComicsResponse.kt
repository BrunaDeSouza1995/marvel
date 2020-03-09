package com.poc.bruna.marvel.plugin.model.response

data class CharacterComicsResponse(
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
        val count: String,
        val results: List<Result>
    ) {
        data class Result(
            val id: String,
            val digitalId: String,
            val title: String,
            val issueNumber: String,
            val variantDescription: String,
            val description: String,
            val modified: String,
            val isbn: String,
            val upc: String,
            val diamondCode: String,
            val ean: String,
            val issn: String,
            val format: String,
            val pageCount: String,
            val textObjects: List<TextObject>,
            val resourceURI: String,
            val urls: List<Url>,
            val series: Series,
            val variants: List<Variant>,
            val collections: List<Collection>,
            val collectedIssues: List<CollectedIssue>,
            val dates: List<Date>,
            val prices: List<Price>,
            val thumbnail: Thumbnail,
            val images: List<Image>,
            val creators: Creators,
            val characters: Characters,
            val stories: Stories,
            val events: Events
        ) {
            data class TextObject(
                val type: String,
                val language: String,
                val text: String
            )

            data class Url(
                val type: String,
                val url: String
            )

            data class Series(
                val resourceURI: String,
                val name: String
            )

            data class Variant(
                val resourceURI: String,
                val name: String
            )

            data class Collection(
                val resourceURI: String,
                val name: String
            )

            data class CollectedIssue(
                val resourceURI: String,
                val name: String
            )

            data class Date(
                val type: String,
                val date: String
            )

            data class Price(
                val type: String,
                val price: String
            )

            data class Thumbnail(
                val path: String,
                val extension: String
            ) {
                fun getStandardFantastic(): String {
                    return "$path/standard_fantastic.$extension"
                }
            }

            data class Image(
                val path: String,
                val extension: String
            )

            data class Creators(
                val available: String,
                val returned: String,
                val collectionURI: String,
                val items: List<Item>
            ) {
                data class Item(
                    val resourceURI: String,
                    val name: String,
                    val role: String
                )
            }

            data class Characters(
                val available: String,
                val returned: String,
                val collectionURI: String,
                val items: List<Item>
            ) {
                data class Item(
                    val resourceURI: String,
                    val name: String,
                    val role: String
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
        }
    }
}