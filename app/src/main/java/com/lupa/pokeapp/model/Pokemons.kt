package com.lupa.pokeapp.model

import com.lupa.pokeapp.room.PokemonEntity

data class Pokemons(
    val id: String = "",
    val name: String = "",
    val desc: String = "",
    val types: List<String>,
    val stats: List<Stat>,
    val abilities: List<String>,
    val evolutionChain: EvolutionChain
) {
    fun mapToEntity(): PokemonEntity =
        PokemonEntity(id, name, desc, types, stats, abilities, evolutionChain)
}