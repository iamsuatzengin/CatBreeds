package com.suatzengin.catbreeds.presentation.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suatzengin.catbreeds.data.local.FavoritesModel
import com.suatzengin.catbreeds.databinding.FavoritesItemBinding

class FavoritesViewHolder(
    private val binding: FavoritesItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        favoriteItem: FavoritesModel,
        onClickFav: (FavoritesModel) -> Boolean,
        deleteFromFav: (FavoritesModel) -> Unit
    ) {
        binding.item = favoriteItem

        binding.favoriteCheckbox.isChecked = onClickFav(favoriteItem)

        binding.favoriteCheckbox.setOnClickListener {
            deleteFromFav(favoriteItem)
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