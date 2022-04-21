package com.suatzengin.catbreeds.data.remote

import com.suatzengin.catbreeds.data.remote.dto.CatBreedDto
import retrofit2.http.GET
import retrofit2.http.Query


interface CatBreedsApi {

    @GET("v1/breeds")
    suspend fun getCatBreeds(): List<CatBreedDto>

    @GET("v1/breeds/search")
    suspend fun searchCatBreed(@Query("q") cat: String): List<CatBreedDto>
}