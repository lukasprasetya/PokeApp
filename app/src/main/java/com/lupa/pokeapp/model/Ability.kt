package com.lupa.pokeapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ability(
    @SerializedName("is_hidden") val isHidden: Boolean = false,
    val ability: Resource
) : Parcelable