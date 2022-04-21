package com.suatzengin.catbreeds.presentation.cat_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suatzengin.catbreeds.databinding.CatBreedsItemBinding
import com.suatzengin.catbreeds.domain.model.CatBreed

class CatBreedListViewHolder(
    private val binding: CatBreedsItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(catBreed: CatBreed) {
        binding.catBreed = catBreed
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