package com.suatzengin.catbreeds.presentation.cat_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.suatzengin.catbreeds.databinding.CatBreedsItemBinding
import com.suatzengin.catbreeds.domain.model.CatBreed
import com.suatzengin.catbreeds.presentation.favorites.FavoritesEvent
import com.suatzengin.catbreeds.presentation.favorites.FavoritesViewModel

class CatBreedListViewHolder(
    private val binding: CatBreedsItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        catBreed: CatBreed,
        viewModel: FavoritesViewModel
    ) {
        binding.catBreed = catBreed

        binding.favorite.apply {
            setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.handleEvent(FavoritesEvent.AddToFavorites(catBreed))
                } else {
                    viewModel.handleEvent(FavoritesEvent.DeleteCatFromFavorites(catBreed))
                }
            }
        }

        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): CatBreedListViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = CatBreedsItemBinding.inflate(layoutInflater, parent, false)
            return CatBreedListViewHolder(binding)
        }
    }
}