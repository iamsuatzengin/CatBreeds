package com.suatzengin.catbreeds.domain.use_case.search_cat_breed

import com.suatzengin.catbreeds.common.Resource
import com.suatzengin.catbreeds.data.remote.dto.toCatBreed
import com.suatzengin.catbreeds.domain.model.CatBreed
import com.suatzengin.catbreeds.domain.repository.CatBreedsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchCatBreedUseCase @Inject constructor(
    private val repository: CatBreedsRepository
) {
    operator fun invoke(breedId: String): Flow<Resource<List<CatBreed>>> = flow {
        try {
            emit(Resource.Loading<List<CatBreed>>())
            val searchedCatBreed = repository.searchCatBreed(breedId).map { it.toCatBreed() }
            emit(Resource.Success<List<CatBreed>>(searchedCatBreed))
        } catch (e: HttpException) {
            emit(
                Resource.Error<List<CatBreed>>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<List<CatBreed>>("Couldn't reach server. Check your internet connection."))
        }
    }
}