package com.lupa.pokeapp.usecase

import com.lupa.pokeapp.model.PokemonSimple
import com.lupa.pokeapp.network.NetworkState
import com.lupa.pokeapp.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend fun invoke() : NetworkState<List<PokemonSimple>> = try {
        val pokemonList = withContext(Dispatchers.IO) {
            repository.getPokemonList()
        }
        NetworkState.Success(pokemonList)
    } catch (e: Throwable) {
        NetworkState.Error(e)
    }
}