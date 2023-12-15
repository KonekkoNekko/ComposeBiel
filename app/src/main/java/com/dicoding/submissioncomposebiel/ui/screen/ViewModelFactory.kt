package com.dicoding.submissioncomposebiel.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.submissioncomposebiel.data.AnimeRepository
import com.dicoding.submissioncomposebiel.ui.screen.home.HomeViewModel
import com.dicoding.submissioncomposebiel.ui.screen.detail.DetailViewModel

class ViewModelFactory(private val animeRepository: AnimeRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                HomeViewModel(animeRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                DetailViewModel(animeRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

//package com.dicoding.submissioncomposebiel.ui.screen
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.dicoding.submissioncomposebiel.data.AnimeRepository
//import com.dicoding.submissioncomposebiel.ui.screen.home.HomeViewModel
//
//class ViewModelFactory(private val animeRepository: AnimeRepository) : ViewModelProvider.Factory {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return HomeViewModel(animeRepository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
