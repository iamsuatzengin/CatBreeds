package com.suatzengin.catbreeds.domain.repository


import com.suatzengin.catbreeds.data.remote.dto.CatBreedDto
import com.suatzengin.catbreeds.domain.model.CatBreed

interface CatBreedsRepository {

    suspend fun getCatBreeds(): List<CatBreedDto>

    suspend fun searchCatBreed(cat: String): List<CatBreedDto>

    suspend fun insert(cat: CatBreed)

    suspend fun delete(cat: CatBreed)

    fun getFavorites(): List<CatBreed>

    fun getFavoriteById(id: Int): CatBreed
}