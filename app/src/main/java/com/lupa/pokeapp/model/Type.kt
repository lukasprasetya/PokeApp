package com.lupa.pokeapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TypeSlot(
    val type: Resource
) : Parcelable