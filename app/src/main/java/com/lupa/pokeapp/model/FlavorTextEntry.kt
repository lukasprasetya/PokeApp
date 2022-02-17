package com.lupa.pokeapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FlavorTextEntry(
    @SerializedName("flavor_text") val flavorText: String = "",
    val language: Resource,
    val version: Resource
) : Parcelable
