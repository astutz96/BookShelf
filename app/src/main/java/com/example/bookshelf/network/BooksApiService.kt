package com.example.bookshelf.network

import com.example.bookshelf.model.Book
import com.example.bookshelf.model.Volume
import retrofit2.http.GET
import retrofit2.http.Path

interface BooksApiService {

    @GET("volumes?q=hi")
    suspend fun getVolume(): Volume

    @GET("volumes/{id}")
    suspend fun getBook(@Path("id") id : String) : Book

}