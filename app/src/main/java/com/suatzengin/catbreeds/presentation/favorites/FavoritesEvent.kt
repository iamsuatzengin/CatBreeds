package com.suatzengin.catbreeds.presentation.favorites

import com.suatzengin.catbreeds.data.local.FavoritesModel

sealed class FavoritesEvent {
    object GetAllFavorites: FavoritesEvent()
    data class AddToFavorites(val cat: FavoritesModel) : FavoritesEvent()
    data class DeleteCatFromFavorites(val cat: FavoritesModel) : FavoritesEvent()
    data class GetFavoriteById(val id: Int) : FavoritesEvent()
}
