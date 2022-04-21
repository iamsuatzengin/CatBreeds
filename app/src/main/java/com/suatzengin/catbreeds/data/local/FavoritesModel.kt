package com.suatzengin.catbreeds.data.local


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.suatzengin.catbreeds.domain.model.CatBreed
import kotlinx.parcelize.Parcelize


@Entity(tableName = "favorites")
@Parcelize
data class FavoritesModel(
    @PrimaryKey(autoGenerate = false) val id: String,
    val name: String?,
    val imageUrl: String?,
    val description: String?,
    val wikipediaUrl: String?,
    val imperialWeight: String?,
    val metricWeight: String?,
    val temperament: String?,
    val childFriendly: Int?,
    val dogFriendly: Int?,
    val lifeSpan: String?,
    val origin: String?,
    var isFavorited: Boolean = false,
    val referenceImageId: String?,
): Parcelable


