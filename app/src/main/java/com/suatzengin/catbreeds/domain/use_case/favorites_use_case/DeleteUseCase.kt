package com.suatzengin.catbreeds.domain.use_case.favorites_use_case

import com.suatzengin.catbreeds.common.Resource
import com.suatzengin.catbreeds.domain.model.CatBreed
import com.suatzengin.catbreeds.domain.repository.CatBreedsRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteUseCase @Inject constructor(
    private val repository: CatBreedsRepository
) {

    operator fun invoke(cat: CatBreed) = flow {
        try {
            emit(Resource.Loading())
            val deleteCat = repository.delete(cat)
            emit(Resource.Success(deleteCat))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}