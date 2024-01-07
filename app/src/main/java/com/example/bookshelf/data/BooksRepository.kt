package com.example.bookshelf.data

import com.example.bookshelf.model.Books
import com.example.bookshelf.network.BooksApiService

interface BooksRepository {
    suspend fun getBooks(): List<Books>
}

class DefaultBooksRepository(
    private val booksRepository: BooksApiService
) : BooksRepository {
    override suspend fun getBooks(): List<Books> {
        return booksRepository.getBooks();
    }
}