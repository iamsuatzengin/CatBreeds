package com.suatzengin.catbreeds.presentation.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suatzengin.catbreeds.databinding.FavoritesItemBinding
import com.suatzengin.catbreeds.domain.model.CatBreed

class FavoritesViewHolder(
    private val binding: FavoritesItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        cat: CatBreed,
        onClickFav: (CatBreed) -> Boolean,
        deleteFromFav: (CatBreed) -> Unit
    ) {
        binding.item = cat

        binding.favoriteCheckbox.isChecked = onClickFav(cat)

        binding.favoriteCheckbox.setOnClickListener {
            deleteFromFav(cat)
        }

        binding.executePendingBindings()
    }


    companion object {
        fun from(parent: ViewGroup): FavoritesViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = FavoritesItemBinding.inflate(layoutInflater, parent, false)
            return FavoritesViewHolder(binding)
        }
    }
}