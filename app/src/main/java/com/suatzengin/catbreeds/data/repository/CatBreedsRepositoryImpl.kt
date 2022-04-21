package com.suatzengin.catbreeds.data.repository

import com.suatzengin.catbreeds.data.remote.CatBreedsApi
import com.suatzengin.catbreeds.data.remote.dto.CatBreedDto
import com.suatzengin.catbreeds.domain.repository.CatBreedsRepository
import javax.inject.Inject

class CatBreedsRepositoryImpl @Inject constructor(
    private val api: CatBreedsApi
) : CatBreedsRepository {
    override suspend fun getCatBreeds(): List<CatBreedDto> {
        return api.getCatBreeds()
    }

    override suspend fun searchCatBreed(cat: String): List<CatBreedDto> {
        return api.searchCatBreed(cat)
    }
}