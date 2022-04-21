package com.suatzengin.catbreeds.presentation.favorites.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.suatzengin.catbreeds.data.local.FavoritesModel

class FavoritesAdapter(
    private val onClickFav: (FavoritesModel) -> Boolean,
    private val deleteFromFav: (FavoritesModel) -> Unit
) : ListAdapter<FavoritesModel, FavoritesViewHolder>(FavDiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val favoriteItem = getItem(position)
        holder.bind(favoriteItem, onClickFav, deleteFromFav)
    }

    companion object FavDiffCallBack : DiffUtil.ItemCallback<FavoritesModel>() {
        override fun areItemsTheSame(oldItem: FavoritesModel, newItem: FavoritesModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FavoritesModel, newItem: FavoritesModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

}