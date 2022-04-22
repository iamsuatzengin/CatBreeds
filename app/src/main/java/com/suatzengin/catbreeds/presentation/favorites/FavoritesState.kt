package com.suatzengin.catbreeds.presentation.favorites

import com.suatzengin.catbreeds.domain.model.CatBreed

data class FavoritesState(
    val isLoading: Boolean = false,
    val favoriteList: List<CatBreed> = emptyList(),
    val error: String = ""
)
