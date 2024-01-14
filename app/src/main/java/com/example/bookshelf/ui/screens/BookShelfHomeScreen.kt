package com.example.bookshelf.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bookshelf.R
import com.example.bookshelf.model.Book

@Composable
fun BookShelfHomeScreen(bookShelfUiState : BookShelfUiState){
    when(bookShelfUiState){
        is BookShelfUiState.Loading -> {
            println("Loading")
            LoadingScreen(modifier = Modifier.fillMaxSize())
        }
        is BookShelfUiState.Success -> {
            println(bookShelfUiState.books)
        }

        is BookShelfUiState.Error -> {
            ErrorScreen(retryAction = { /*TODO*/ }, modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun PhotosGridScreen(photos: List<Book>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier,
        contentPadding = PaddingValues(4.dp)
    ) {

    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = "Loading"
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = "Failed to load", modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text("Retry")
        }
    }
}