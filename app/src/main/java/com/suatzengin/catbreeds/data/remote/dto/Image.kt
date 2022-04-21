package com.suatzengin.catbreeds.data.remote.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val height: Int?,
    val width: Int?,
    val id: String?,
    val url: String?,
): Parcelable
