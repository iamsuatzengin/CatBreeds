package com.suatzengin.catbreeds.presentation.favorites

import com.suatzengin.catbreeds.domain.model.CatBreed

sealed class FavoritesEvent {
    object GetAllFavorites: FavoritesEvent()
    data class AddToFavorites(val cat: CatBreed) : FavoritesEvent()
    data class DeleteCatFromFavorites(val cat: CatBreed) : FavoritesEvent()
    data class GetFavoriteById(val id: Int) : FavoritesEvent()
}
