package com.suatzengin.catbreeds.presentation.favorites.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.suatzengin.catbreeds.domain.model.CatBreed

class FavoritesAdapter(
    private val onClickFav: (CatBreed) -> Boolean,
    private val deleteFromFav: (CatBreed) -> Unit
) : ListAdapter<CatBreed, FavoritesViewHolder>(FavDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val favoriteItem = getItem(position)
        holder.bind(favoriteItem, onClickFav, deleteFromFav)
    }

    companion object FavDiffCallBack : DiffUtil.ItemCallback<CatBreed>() {
        override fun areItemsTheSame(oldItem: CatBreed, newItem: CatBreed): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CatBreed, newItem: CatBreed): Boolean {
            return oldItem.id == newItem.id
        }
    }

}