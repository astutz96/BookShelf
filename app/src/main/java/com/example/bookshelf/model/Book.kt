package com.example.bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val kind : String,
    val id : String,
    val volumeInfo : VolumeInfo
)

@Serializable
data class VolumeInfo(
    val title : String,
    val imageLinks : ImageLinks
)

@Serializable
data class ImageLinks(
    val smallThumbnail : String,
    val thumbnail : String,
    val small : String,
    val medium : String
)
