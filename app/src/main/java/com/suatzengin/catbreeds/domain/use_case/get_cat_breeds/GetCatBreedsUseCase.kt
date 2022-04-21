package com.suatzengin.catbreeds.domain.use_case.get_cat_breeds

import com.suatzengin.catbreeds.common.Resource
import com.suatzengin.catbreeds.data.remote.dto.toCatBreed
import com.suatzengin.catbreeds.domain.model.CatBreed
import com.suatzengin.catbreeds.domain.repository.CatBreedsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCatBreedsUseCase @Inject constructor(
    private val repository: CatBreedsRepository
) {
    operator fun invoke(): Flow<Resource<List<CatBreed>>> = flow {
        try {
            emit(Resource.Loading<List<CatBreed>>())
            val catBreeds = repository.getCatBreeds().map { it.toCatBreed() }
            emit(Resource.Success<List<CatBreed>>(catBreeds))
        } catch (e: HttpException) {
            emit(
                Resource.Error<List<CatBreed>>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch(e: IOException) {
            emit(Resource.Error<List<CatBreed>>("Couldn't reach server. Check your internet connection."))
        }
    }
}