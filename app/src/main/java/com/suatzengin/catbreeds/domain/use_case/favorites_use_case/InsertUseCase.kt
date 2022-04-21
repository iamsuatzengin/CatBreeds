package com.suatzengin.catbreeds.domain.use_case.favorites_use_case

import com.suatzengin.catbreeds.common.Resource
import com.suatzengin.catbreeds.data.local.FavoritesModel
import com.suatzengin.catbreeds.domain.repository.CatBreedsRepository
import kotlinx.coroutines.flow.flow
import java.lang.Exception

import javax.inject.Inject

class InsertUseCase @Inject constructor(
    private val repository: CatBreedsRepository
) {

    operator fun invoke(cat: FavoritesModel) = flow {
        try {
            emit(Resource.Loading())
            val insertingCat = repository.insert(cat)
            emit(Resource.Success(insertingCat))

        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}