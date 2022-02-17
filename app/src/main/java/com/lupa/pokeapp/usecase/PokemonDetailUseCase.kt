package com.lupa.pokeapp.usecase

import com.lupa.pokeapp.model.Pokemons
import com.lupa.pokeapp.network.NetworkState
import com.lupa.pokeapp.repository.PokeApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonDetailUseCase @Inject constructor(
    private val repository: PokeApiRepository
) {
    suspend fun invoke(id: String) : NetworkState<Pokemons> = try {
        val pokemon = withContext(Dispatchers.IO) {
            repository.getPokemon(id)
        }
        NetworkState.Success(pokemon)
    } catch (e: Throwable) {
        NetworkState.Error(e)
    }
}