package com.lupa.pokeapp.mapper

import com.lupa.pokeapp.model.*
import com.lupa.pokeapp.utils.PokemonUtils

object PokemonToUIMapper : BaseMapper<Pokemons, PokemonDetail> {
    override fun map(type: Pokemons): PokemonDetail {
        val color = if (type.types.isNotEmpty()) {
            PokemonUtils.getPokemonTypeColor(type.types.first())
        } else {
            PokemonUtils.getPokemonTypeColor("")
        }
        val stats = type.stats.map {
            Stat(PokemonUtils.getStatName(it.name), it.value)
        }

        val abilities = type.abilities.map {
            it.replace("-", "").uppercase()
        }

        val types = type.types.map {
            it.replaceFirstChar { c -> c.uppercase() }
        }

        val evolutions: ArrayList<Evolution> = arrayListOf()
        mapEvolutionChainToList(type.evolutionChain, evolutions)

        return PokemonDetail(
            type.id,
            type.name,
            type.desc,
            PokemonUtils.getImageUrl(type.id),
            color,
            types,
            stats,
            abilities,
            evolutions
        )
    }

    private fun mapEvolutionChainToList(
        evolutionChain: EvolutionChain,
        evolutions: ArrayList<Evolution>
    ) {

        for (evolve in evolutionChain.evolvesTo) {
            val evolution = Evolution(
                evolutionChain.id,
                evolutionChain.name,
                evolve.id,
                evolve.name,
                evolve.condition
            )
            evolutions.add(evolution)
            mapEvolutionChainToList(evolve, evolutions)
        }
    }
}