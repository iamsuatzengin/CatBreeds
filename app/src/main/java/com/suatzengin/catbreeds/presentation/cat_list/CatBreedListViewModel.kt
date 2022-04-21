package com.suatzengin.catbreeds.presentation.cat_list


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suatzengin.catbreeds.common.Resource
import com.suatzengin.catbreeds.domain.model.CatBreed
import com.suatzengin.catbreeds.domain.use_case.get_cat_breeds.GetCatBreedsUseCase
import com.suatzengin.catbreeds.domain.use_case.search_cat_breed.SearchCatBreedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CatBreedListViewModel @Inject constructor(
    private val getCatBreedsUseCase: GetCatBreedsUseCase,
    private val searchCatBreedUseCase: SearchCatBreedUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CatBreedListState())
    val state: StateFlow<CatBreedListState> = _state

    private val _searchState = MutableLiveData<List<CatBreed>>()
    val searchState: LiveData<List<CatBreed>> = _searchState

    init {
        getCatBreeds()
    }

    private fun getCatBreeds() {
        getCatBreedsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CatBreedListState(catBreeds = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value =
                        CatBreedListState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = CatBreedListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun searchCatBreed(cat: String) {
        viewModelScope.launch {
            val deneme = searchCatBreedUseCase.invoke(cat).toList()
            deneme.forEach {
                _searchState.value = it.data ?: emptyList()
            }
        }
    }

}

