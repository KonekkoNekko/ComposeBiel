package com.dicoding.submissioncomposebiel.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.submissioncomposebiel.model.Anime
import com.dicoding.submissioncomposebiel.ui.component.AnimeItem
import com.dicoding.submissioncomposebiel.ui.theme.SubmissionComposeBielTheme

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel(), navigateToDetail: (Long) -> Unit) {
    val animeList = homeViewModel.animeList.collectAsState().value
    Column(modifier = Modifier.fillMaxWidth()) {
        HomeContent(
            navigateToDetail = navigateToDetail, animes = animeList
        )
    }
}

@Composable
fun HomeContent(navigateToDetail: (Long) -> Unit, animes: List<Anime>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(8.dp)){
        items(animes){
            AnimeItem(id = it.id, title = it.title, year = it.year, modifier = Modifier.clickable{ navigateToDetail(it.id) })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SubmissionComposeBielTheme {
        HomeScreen(
            navigateToDetail = {}
        )
    }
}