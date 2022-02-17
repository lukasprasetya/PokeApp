package com.lupa.pokeapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonData(
    val id: String = "",
    val name: String = "",
    val height: Int = 0,
    val weight: Int = 0,
    val types: List<TypeSlot>,
    val stats: List<Stats>,
    val abilities: List<Ability>
) : Parcelable