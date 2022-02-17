package com.lupa.pokeapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stat(
    val name: String = "",
    val value: Int = 0
) : Parcelable