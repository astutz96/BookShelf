package com.example.bookshelf.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Books(
    @SerializedName("kind")
    val kind : String
)
