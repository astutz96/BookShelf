package com.example.bookshelf.data

import com.example.bookshelf.model.Book
import com.example.bookshelf.model.Volume
import com.example.bookshelf.network.BooksApiService

interface BooksRepository {
    suspend fun getVolume(): Volume

    suspend fun getBook(id : String): Book
}

class DefaultBooksRepository(
    private val booksService: BooksApiService
) : BooksRepository {

    override suspend fun getVolume(): Volume {
        return booksService.getVolume()
    }

    override suspend fun getBook(id : String): Book {
        return booksService.getBook(id)
    }
}