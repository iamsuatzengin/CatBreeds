package com.suatzengin.catbreeds.domain.repository

import com.suatzengin.catbreeds.data.remote.dto.CatBreedDto

interface CatBreedsRepository {

    suspend fun getCatBreeds(): List<CatBreedDto>

    suspend fun searchCatBreed(cat: String): List<CatBreedDto>
}