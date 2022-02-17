package com.lupa.pokeapp.repository

import com.lupa.pokeapp.model.PokemonSimple

interface PokemonRepository {
    suspend fun getPokemonList(): List<PokemonSimple>
}