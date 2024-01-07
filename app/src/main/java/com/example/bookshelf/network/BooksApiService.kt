package com.example.bookshelf.network

import com.example.bookshelf.model.Books
import com.example.bookshelf.model.Volume
import retrofit2.http.GET

interface BooksApiService {
    @GET("volumes?q=hi")
    suspend fun getBooks(): List<Books>

    @GET("volumes?q=hi")
    suspend fun getVolume(): Volume
}