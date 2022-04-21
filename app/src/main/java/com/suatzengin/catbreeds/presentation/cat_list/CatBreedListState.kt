package com.suatzengin.catbreeds.presentation.cat_list

import com.suatzengin.catbreeds.domain.model.CatBreed

data class CatBreedListState(
    val isLoading: Boolean = false,
    val catBreeds: List<CatBreed> = emptyList(),
    val error: String = ""
)
