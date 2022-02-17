package com.lupa.pokeapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Stats(
    @SerializedName("base_stat") val baseStat: Int = 0,
    val effort: Int = 0,
    val stat: Resource
) : Parcelable
