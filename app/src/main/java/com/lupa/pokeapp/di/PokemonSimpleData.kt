package com.lupa.pokeapp.di

import android.os.Parcelable
import com.lupa.pokeapp.model.PokemonSimple
import com.lupa.pokeapp.room.PokemonSimpleEntity
import kotlinx.parcelize.Parcelize


@Parcelize
class PokemonSimpleData (
    val id: String = "",
    val name: String = "",
    val type1: String = "",
    val type2: String = "",
    val legendary: Boolean = false
) : Parcelable

fun PokemonSimpleData.mapToDomain(): PokemonSimple {
    return PokemonSimple(
        id,
        name,
        type1,
        type2
    )
}

fun PokemonSimpleData.mapToEntity(): PokemonSimpleEntity {
    return PokemonSimpleEntity(
        id,
        name,
        type1,
        type2,
        legendary
    )
}
