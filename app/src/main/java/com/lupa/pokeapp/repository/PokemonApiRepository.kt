package com.lupa.pokeapp.repository

import com.lupa.pokeapp.model.Pokemons
import com.lupa.pokeapp.network.PokemonApi
import com.lupa.pokeapp.network.PokemonMapper
import com.lupa.pokeapp.room.PokemonDao
import com.lupa.pokeapp.room.mapToDomain
import com.lupa.pokeapp.utils.StringUtils
import javax.inject.Inject

class PokemonApiRepository @Inject constructor(
    private val api: PokemonApi,
    private val dao: PokemonDao
) : PokeApiRepository {
    override suspend fun getPokemon(id: String): Pokemons {
        val cachedPokemon = dao.getById(id)

        return if (cachedPokemon == null) {
            val pokemonDto = api.getPokemon(id)
            val pokemonSpeciesDto = api.getPokemonSpecies(id)
            val pokemonChainId = StringUtils.getIdFromUrl(pokemonSpeciesDto.evolutionChain.url)
            val evolutionChainDto = api.getEvolutionChain(pokemonChainId)

            val pokemon = PokemonMapper.mapToDomain(pokemonDto, pokemonSpeciesDto, evolutionChainDto)

            dao.insert(pokemon.mapToEntity())

            pokemon
        } else {
            cachedPokemon.mapToDomain()
        }
    }

}