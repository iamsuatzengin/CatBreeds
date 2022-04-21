package com.suatzengin.catbreeds.domain.repository

import com.suatzengin.catbreeds.data.local.FavoritesModel
import com.suatzengin.catbreeds.data.remote.dto.CatBreedDto

interface CatBreedsRepository {

    suspend fun getCatBreeds(): List<CatBreedDto>

    suspend fun searchCatBreed(cat: String): List<CatBreedDto>

    suspend fun insert(cat: FavoritesModel)

    suspend fun delete(cat: FavoritesModel)

    fun getFavorites(): List<FavoritesModel>

    fun getFavoriteById(id: Int): FavoritesModel
}