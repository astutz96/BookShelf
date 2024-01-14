package com.example.bookshelf.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.model.Book

@Composable
fun BookShelfHomeScreen(bookShelfUiState : BookShelfUiState, retryAction: () -> Unit){
    when(bookShelfUiState){
        is BookShelfUiState.Loading -> {
            println("Loading")
            LoadingScreen(modifier = Modifier.fillMaxSize())
        }
        is BookShelfUiState.Success -> {
            PhotosGridScreen(books = bookShelfUiState.books)
        }

        is BookShelfUiState.Error -> {
            ErrorScreen(retryAction = { retryAction}, modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun PhotosGridScreen(books: List<Book>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier,
        contentPadding = PaddingValues(2.dp)
    ) {
        items(items = books, key = {book -> book.id}) {book ->
            BookShelfItemCard(book)
        }
    }
}

@Composable
fun BookShelfItemCard(book : Book){
    Card(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()
            .aspectRatio(1.5f),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    )
    {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current).data(book.volumeInfo.imageLinks.thumbnail.replace("http", "https"))
                .crossfade(true).build(),
            error = painterResource(R.drawable.ic_broken_image),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = book.volumeInfo.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
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