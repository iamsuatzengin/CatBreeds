package com.suatzengin.catbreeds.domain.use_case.favorites_use_case

import com.suatzengin.catbreeds.common.Resource
import com.suatzengin.catbreeds.domain.repository.CatBreedsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GettAllUseCase @Inject constructor(
    private val repository: CatBreedsRepository
) {
    operator fun invoke() = flow {
        try {
            emit(Resource.Loading())
            val getAllFavorites = repository.getFavorites()
            emit(Resource.Success(getAllFavorites))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}