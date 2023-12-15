package com.dicoding.submissioncomposebiel.data

import com.dicoding.submissioncomposebiel.model.Anime
import com.dicoding.submissioncomposebiel.model.FakeDataSource

class AnimeRepository {
    fun getAnimes(): List<Anime> {
        return FakeDataSource.dummyAnimes
    }

    fun getAnimeById(id: Long): Anime? {
        return FakeDataSource.dummyAnimes.find { it.id == id }
    }
}
