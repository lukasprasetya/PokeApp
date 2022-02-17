package com.lupa.pokeapp.model

import androidx.annotation.ColorRes

data class PokemonDetail(
    val id: String = "",
    val name: String = "",
    val desc: String = "",
    val imageUrl: String = "",
    @ColorRes val color: Int,
    val types: List<String>,
    val stats: List<Stat>,
    val abilities: List<String>,
    val evolutions: List<Evolution>
)