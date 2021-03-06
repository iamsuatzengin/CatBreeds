package com.suatzengin.catbreeds.data.remote.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weight(
    val imperial: String?,
    val metric: String?
): Parcelable
