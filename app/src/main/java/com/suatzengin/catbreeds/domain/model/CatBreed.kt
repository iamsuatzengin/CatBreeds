package com.suatzengin.catbreeds.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorites")
data class CatBreed(
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
) : Parcelable
