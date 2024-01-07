package com.example.bookshelf.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookShelfApplication
import com.example.bookshelf.data.BooksRepository
import kotlinx.coroutines.launch

class BookShelfViewModel(private val bookShelfRepository: BooksRepository) : ViewModel(){

    fun getBooks() {
        viewModelScope.launch {
            val books = bookShelfRepository.getBooks()
            println(books)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as BookShelfApplication)
                val booksRepository = application.container.booksRepository
                BookShelfViewModel(bookShelfRepository = booksRepository)
            }
        }
    }
}