package com.suatzengin.catbreeds.presentation.favorites

import com.suatzengin.catbreeds.data.local.FavoritesModel

data class FavoritesState(
    val isLoading: Boolean = false,
    val favoriteList: List<FavoritesModel> = emptyList(),
    val error: String = ""
)
