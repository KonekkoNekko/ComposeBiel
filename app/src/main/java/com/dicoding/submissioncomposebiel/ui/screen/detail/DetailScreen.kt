package com.dicoding.submissioncomposebiel.ui.screen.detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.submissioncomposebiel.R
import com.dicoding.submissioncomposebiel.data.AnimeRepository
import com.dicoding.submissioncomposebiel.ui.screen.ViewModelFactory
import com.dicoding.submissioncomposebiel.ui.screen.home.HomeScreen
import com.dicoding.submissioncomposebiel.ui.theme.SubmissionComposeBielTheme

@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    id: Long,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Assume Anime has an id, title, description, imageUrl, etc.
    val anime by viewModel.animeDetails.collectAsState()

    LaunchedEffect(id) {
        viewModel.loadAnimeDetails(id)
    }

    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)) {
        anime?.let {
            val animeId = it.id
            val resourceName = "anime_$animeId"
            val stringIMG = try {
                val resId = R.drawable::class.java.getField(resourceName).getInt(null)
                resId
            } catch (e: Exception) {
                Log.d("Anime Item", "No Image found!")
            }
            Image(
                painter = painterResource(id = stringIMG),
                contentDescription = "Poster anime",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(180.dp)
                    .width(120.dp)
            )
            Text(text = it.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = it.description, style = MaterialTheme.typography.bodyMedium)
            // Add more UI elements here as needed, like Image, Rating, etc.
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = navigateBack) {
            Text("Go Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    SubmissionComposeBielTheme {
        val animeRepository = remember { AnimeRepository() } // Create an instance of AnimeRepository
        val viewModel: DetailViewModel = viewModel(
            factory = ViewModelFactory(animeRepository)
        )
        DetailScreen(viewModel = viewModel, id = 2, navigateBack = {  })
    }
}