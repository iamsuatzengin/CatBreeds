package com.suatzengin.catbreeds.domain.use_case.favorites_use_case

import com.suatzengin.catbreeds.common.Resource
import com.suatzengin.catbreeds.domain.repository.CatBreedsRepository
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetByIdUseCase @Inject constructor(
    private val repository: CatBreedsRepository
) {

    operator fun invoke(id: String) = flow {
        try {
            emit(Resource.Loading())
            val getFavoriteById = repository.getFavoriteById(id)
            emit(Resource.Success(getFavoriteById))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}