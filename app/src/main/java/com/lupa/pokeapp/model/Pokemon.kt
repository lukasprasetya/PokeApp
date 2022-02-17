package com.lupa.pokeapp.model

import androidx.annotation.ColorRes

data class Pokemon(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val type1: String = "",
    val type2: String = "",
    @ColorRes val cardColor: Int = 0
)