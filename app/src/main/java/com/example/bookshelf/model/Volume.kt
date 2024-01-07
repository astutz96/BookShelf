package com.example.bookshelf.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

val format = Json{ ignoreUnknownKeys = true}
@Serializable
data class Volume(
    @SerializedName("kind")
    val kind : String,
    val totalItems: Int,
    val items : List<VolumeItems>
)

@Serializable
data class VolumeItems(
    val kind : String,
    val id : String
)
