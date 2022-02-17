package com.lupa.pokeapp.network

import com.lupa.pokeapp.model.EvolutionChainData
import com.lupa.pokeapp.model.PokemonData
import com.lupa.pokeapp.model.PokemonKind
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: String
    ): PokemonData

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(
        @Path("id") id: String
    ): PokemonKind

    @GET("evolution-chain/{id}")
    suspend fun getEvolutionChain(
        @Path("id") id: String
    ): EvolutionChainData
}