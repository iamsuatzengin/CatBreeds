package com.suatzengin.catbreeds.presentation.cat_list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.suatzengin.catbreeds.domain.model.CatBreed
import com.suatzengin.catbreeds.presentation.favorites.FavoritesViewModel
//private val onClickFav: (CatBreed) -> Unit,
//    private val onClickDeleteFav: (CatBreed) -> Unit,
class CatBreedListAdapter(
    private val favoritesViewModel: FavoritesViewModel

): ListAdapter<CatBreed, CatBreedListViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatBreedListViewHolder {
        return CatBreedListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CatBreedListViewHolder, position: Int) {
        val catBreed = getItem(position)
        holder.bind(catBreed, favoritesViewModel)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<CatBreed>() {
        override fun areItemsTheSame(oldItem: CatBreed, newItem: CatBreed): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CatBreed, newItem: CatBreed): Boolean {
            return oldItem.id == newItem.id
        }

    }
}