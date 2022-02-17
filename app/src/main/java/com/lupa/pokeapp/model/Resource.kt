package com.lupa.pokeapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Resource(
    val name: String,
    val url: String
) : Parcelable