package com.suatzengin.catbreeds.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.catbreeds.data.local.FavoritesModel
import com.suatzengin.catbreeds.domain.use_case.favorites_use_case.DeleteUseCase
import com.suatzengin.catbreeds.domain.use_case.favorites_use_case.GetByIdUseCase
import com.suatzengin.catbreeds.domain.use_case.favorites_use_case.GettAllUseCase
import com.suatzengin.catbreeds.domain.use_case.favorites_use_case.InsertUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val insertUseCase: InsertUseCase,
    private val deleteUseCase: DeleteUseCase,
    private val getAllFavoritesUseCase: GettAllUseCase,
    private val getFavoriteByIdUseCase: GetByIdUseCase
) : ViewModel() {


    fun addToFavorites(cat: FavoritesModel){
        viewModelScope.launch {
            insertUseCase.invoke(cat).collect{}
        }
    }


}