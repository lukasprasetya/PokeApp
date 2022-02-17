package com.lupa.pokeapp.repository

import com.lupa.pokeapp.model.Pokemons

interface PokeApiRepository {

    suspend fun getPokemon(id: String): Pokemons
}