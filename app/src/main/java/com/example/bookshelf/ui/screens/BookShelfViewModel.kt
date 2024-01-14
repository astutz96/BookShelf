package com.example.bookshelf.ui.screens

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookShelfApplication
import com.example.bookshelf.data.BooksRepository
import com.example.bookshelf.model.Book
import kotlinx.coroutines.launch
import okio.IOException

//Define an interface for the various states of loading the BookShelf, this help with offline-first UI design
sealed interface BookShelfUiState{
    data class Success(val books: List<Book>) : BookShelfUiState

    object Loading : BookShelfUiState

    object Error : BookShelfUiState
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class BookShelfViewModel(private val bookShelfRepository: BooksRepository) : ViewModel(){

    //private val _uiState = MutableStateFlow(mutableListOf<Book>())
    //val bookShelfUiState: StateFlow<MutableList<Book>> = _uiState.asStateFlow()

    var bookShelfUiState: BookShelfUiState by mutableStateOf(BookShelfUiState.Loading)
        private set
    init {
        getBooks()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getBooks() {
        viewModelScope.launch {
            bookShelfUiState = BookShelfUiState.Loading
            bookShelfUiState = try {

                val test : MutableList<Book> = mutableListOf<Book>()
                val volume = bookShelfRepository.getVolume()
                for (item in volume.items){
                    test.add(bookShelfRepository.getBook(item.id))
                }
                BookShelfUiState.Success(test.toList())
            } catch (e: IOException){
                BookShelfUiState.Error
            } catch (e: HttpException){
                BookShelfUiState.Error
            }
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