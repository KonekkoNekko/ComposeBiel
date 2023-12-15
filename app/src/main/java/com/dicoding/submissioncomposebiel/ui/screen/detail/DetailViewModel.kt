package com.dicoding.submissioncomposebiel.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.submissioncomposebiel.data.AnimeRepository
import com.dicoding.submissioncomposebiel.model.Anime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: AnimeRepository) : ViewModel() {
    private val _animeDetails = MutableStateFlow<Anime?>(null)
    val animeDetails: StateFlow<Anime?> = _animeDetails

    fun loadAnimeDetails(id: Long) {
        viewModelScope.launch {
            _animeDetails.value = repository.getAnimeById(id)
        }
    }
}
