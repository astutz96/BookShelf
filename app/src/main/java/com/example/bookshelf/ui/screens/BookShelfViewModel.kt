package com.example.bookshelf.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookShelfApplication
import com.example.bookshelf.data.BooksRepository
import com.example.bookshelf.model.Book
import kotlinx.coroutines.launch

class BookShelfViewModel(private val bookShelfRepository: BooksRepository) : ViewModel(){

    init {
        getVolume()
    }

    fun getVolume() {
        viewModelScope.launch {
            val test : MutableList<Book> = mutableListOf<Book>();
            val volume = bookShelfRepository.getVolume()
            for (item in volume.items){
                test.add(bookShelfRepository.getBook(item.id))
            }
            println(test)
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