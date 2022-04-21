package com.suatzengin.catbreeds.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.catbreeds.common.Resource
import com.suatzengin.catbreeds.data.local.FavoritesModel
import com.suatzengin.catbreeds.domain.use_case.favorites_use_case.DeleteUseCase
import com.suatzengin.catbreeds.domain.use_case.favorites_use_case.GetByIdUseCase
import com.suatzengin.catbreeds.domain.use_case.favorites_use_case.GetAllUseCase
import com.suatzengin.catbreeds.domain.use_case.favorites_use_case.InsertUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val insertUseCase: InsertUseCase,
    private val deleteUseCase: DeleteUseCase,
    private val getAllFavoritesUseCase: GetAllUseCase,
    private val getFavoriteByIdUseCase: GetByIdUseCase
) : ViewModel() {

    private val _stateFavorites = MutableStateFlow(FavoritesState())
    val stateFavorites: StateFlow<FavoritesState> = _stateFavorites


    fun handleEvent(event: FavoritesEvent) {
        when (event) {
            is FavoritesEvent.GetAllFavorites -> {
                getAllFavorites()
            }
            is FavoritesEvent.AddToFavorites -> {
                addToFavorites(event.cat)
            }
            is FavoritesEvent.DeleteCatFromFavorites -> {
                deleteFromFavorites(event.cat)
            }
            is FavoritesEvent.GetFavoriteById -> {
                getFavoriteById(event.id)
            }
        }
    }

    private fun getAllFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllFavoritesUseCase().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _stateFavorites.value =
                            FavoritesState(favoriteList = result.data ?: emptyList())
                    }
                    is Resource.Loading -> {
                        _stateFavorites.value = FavoritesState(isLoading = true)
                    }
                    is Resource.Error -> {
                        _stateFavorites.value =
                            FavoritesState(error = result.message ?: "An unexpected error occurred")
                    }
                }
            }
        }
    }

    private fun addToFavorites(cat: FavoritesModel) {
        viewModelScope.launch {
            insertUseCase.invoke(cat).collect {}
        }
    }

    private fun deleteFromFavorites(cat: FavoritesModel) {
        viewModelScope.launch {
            deleteUseCase.invoke(cat).collect {}
        }
    }

    private fun getFavoriteById(id: Int) {
        viewModelScope.launch {
            getFavoriteByIdUseCase.invoke(id).collect {}
        }
    }

    fun isFavorited(cat: FavoritesModel): Boolean {
        if (cat in _stateFavorites.value.favoriteList) {
            return true
        }
        return false
    }
}