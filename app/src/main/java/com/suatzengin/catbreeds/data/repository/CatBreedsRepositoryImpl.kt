package com.suatzengin.catbreeds.data.repository

import com.suatzengin.catbreeds.data.local.FavoritesDao
import com.suatzengin.catbreeds.data.local.FavoritesModel
import com.suatzengin.catbreeds.data.remote.CatBreedsApi
import com.suatzengin.catbreeds.data.remote.dto.CatBreedDto
import com.suatzengin.catbreeds.domain.repository.CatBreedsRepository
import javax.inject.Inject

class CatBreedsRepositoryImpl @Inject constructor(
    private val api: CatBreedsApi,
    private val dao: FavoritesDao
) : CatBreedsRepository {
    override suspend fun getCatBreeds(): List<CatBreedDto> {
        return api.getCatBreeds()
    }

    override suspend fun searchCatBreed(cat: String): List<CatBreedDto> {
        return api.searchCatBreed(cat)
    }

    override suspend fun insert(cat: FavoritesModel) = dao.insert(cat)

    override suspend fun delete(cat: FavoritesModel) = dao.delete(cat)

    override fun getFavorites(): List<FavoritesModel> = dao.getFavorites()

    override fun getFavoriteById(id: Int): FavoritesModel = dao.getFavoriteById(id)
}