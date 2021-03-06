package com.suatzengin.catbreeds.common

import android.graphics.Paint
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.suatzengin.catbreeds.R
import com.suatzengin.catbreeds.domain.model.CatBreed
import com.suatzengin.catbreeds.presentation.cat_detail.CatBreedDetailFragmentDirections
import com.suatzengin.catbreeds.presentation.cat_list.CatBreedListFragmentDirections
import com.suatzengin.catbreeds.presentation.favorites.FavoritesFragmentDirections

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)
    }
}


@BindingAdapter("sendDataToDetailFragment")
fun sendDataToDetailFragment(view: ConstraintLayout, catBreed: CatBreed) {
    view.setOnClickListener {
        val action = CatBreedListFragmentDirections.listToDetail(cat = catBreed)
        view.findNavController().navigate(action)
    }
}

/*
@BindingAdapter("isChecked")
fun isChecked(checkBox: CheckBox, isFavorited: Boolean) {
    checkBox.isChecked = isFavorited
}
*/
@BindingAdapter("underline")
fun underlineForWikiUrl(textView: TextView, cat: CatBreed) {
    textView.paintFlags = textView.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    if (cat.wikipediaUrl.isNullOrEmpty()) {
        textView.text = "No Link"
        textView.isClickable = false
    } else {
        textView.text = cat.wikipediaUrl
        textView.setOnClickListener {
            val action = CatBreedDetailFragmentDirections.detailToWebView(cat)
            textView.findNavController().navigate(action)
        }
    }
}

@BindingAdapter("sendDataFromFavoritesToDetail")
fun sendDataFromFavoritesToDetail(view: ConstraintLayout, catBreed: CatBreed) {
    view.setOnClickListener {
        val action = FavoritesFragmentDirections.favoritesToDetail(catBreed)
        view.findNavController().navigate(action)
    }
}