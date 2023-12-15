package com.dicoding.submissioncomposebiel.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.submissioncomposebiel.data.AnimeRepository
import com.dicoding.submissioncomposebiel.model.Anime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val animeRepository: AnimeRepository? = null) : ViewModel() {

    private val repository: AnimeRepository by lazy {
        animeRepository ?: Companion.provideRepository()
    }

    private val _animeList = MutableStateFlow<List<Anime>>(emptyList())
    val animeList: StateFlow<List<Anime>> = _animeList

    init {
        loadAnimes()
    }

    private fun loadAnimes() {
        viewModelScope.launch {
            _animeList.value = repository.getAnimes()
        }
    }

    companion object {
        fun provideRepository(): AnimeRepository {
            return AnimeRepository()
        }
    }
}
