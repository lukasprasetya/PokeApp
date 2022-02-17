package com.lupa.pokeapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EvolutionChain(
    val id: String = "",
    val name: String = "",
    val condition: String,
    val evolvesTo: List<EvolutionChain>
) : Parcelable