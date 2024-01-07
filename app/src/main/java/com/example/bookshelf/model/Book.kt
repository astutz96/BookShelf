package com.example.bookshelf.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val kind : String,
    val id : String,
    val volumeInfo : VolumeInfo
)

@Serializable
data class VolumeInfo(
    val title : String
)
