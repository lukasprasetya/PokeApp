package com.lupa.pokeapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lupa.pokeapp.model.EvolutionChain
import com.lupa.pokeapp.model.Pokemons
import com.lupa.pokeapp.model.Stat

@Entity
data class PokemonEntity(
    @PrimaryKey val id: String = "",
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "desc") val desc: String = "",
    @ColumnInfo(name = "types") val types: List<String>,
    @ColumnInfo(name = "stats") val stats: List<Stat>,
    @ColumnInfo(name = "abilities") val abilities: List<String>,
    @ColumnInfo(name = "evolution_chain") val evolutionChain: EvolutionChain
)

fun PokemonEntity.mapToDomain(): Pokemons {
    return Pokemons(
        id,
        name,
        desc,
        types,
        stats,
        abilities,
        evolutionChain)
}