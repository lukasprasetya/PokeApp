package com.lupa.pokeapp.di

import retrofit2.http.GET

interface PokeList {

    @GET("pokemon.json")
    suspend fun getPokemonList(): List<PokemonSimpleData>
}