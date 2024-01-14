package com.example.bookshelf.data

import com.example.bookshelf.network.BooksApiService
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

interface AppContainer {
    val booksRepository : BooksRepository
}

class DefaultAppContainer : AppContainer {

    //App Starts Here
    //Upon App Startup these resources will be begin initialization (even before any UI Loads)

    //Create a retrofit instance for the google Books API resource I want to connect to
    private val baseUrl = "https://www.googleapis.com/books/v1/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json{ignoreUnknownKeys= true}.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    //Create a retroFit service for the retroFit instance which has specific methods which can call the specified API resource and automatically adapt the responses into objective I've defined
    private val retrofitService : BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }

    //Create a books Repository, which is basically just a wrapper around a books API service.
    //This API service, as created above, can reach out to a given API endpoint and return data formatted I define
    //Additionally, this is created with dependency injection, allowing us to mack the API service given to the repository.
    override val booksRepository: BooksRepository by lazy {
        DefaultBooksRepository(retrofitService)
    }
}