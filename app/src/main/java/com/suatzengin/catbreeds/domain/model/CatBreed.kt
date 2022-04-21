package com.suatzengin.catbreeds.domain.model

import android.os.Parcelable
import com.suatzengin.catbreeds.data.local.FavoritesModel
import com.suatzengin.catbreeds.data.remote.dto.Image
import com.suatzengin.catbreeds.data.remote.dto.Weight
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatBreed(
    val id: String?,
    val name: String?,
    val image: Image?,
    val description: String?,
    val wikipediaUrl: String?,
    val weight: Weight?,
    val temperament: String?,
    val childFriendly: Int?,
    val dogFriendly: Int?,
    val lifeSpan: String?,
    val origin: String?,
    var isFavorited: Boolean = false,
    val referenceImageId: String?,
) : Parcelable

fun CatBreed.toFavoriteModel(): FavoritesModel{
    return FavoritesModel(
        id = id!!,
        name = name,
        imageUrl = image?.url,
        description = description,
        wikipediaUrl = wikipediaUrl,
        imperialWeight = weight?.imperial,
        metricWeight = weight?.metric,
        temperament = temperament,
        childFriendly = childFriendly,
        dogFriendly = dogFriendly,
        lifeSpan = lifeSpan,
        origin = origin,
        isFavorited = isFavorited,
        referenceImageId = referenceImageId
    )
}