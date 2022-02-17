package com.lupa.pokeapp.network

import com.lupa.pokeapp.model.*
import com.lupa.pokeapp.utils.StringUtils

object PokemonMapper {
    fun mapToDomain(
        pokemonData: PokemonData,
        pokemonKind: PokemonKind,
        evolutionChainData: EvolutionChainData
    ) : Pokemons {
        val types = pokemonData.types.map {
            it.type.name
        }

        val abilities = pokemonData.abilities.map {
            it.ability.name
        }

        val stats = pokemonData.stats.map {
            Stat(it.stat.name, it.baseStat)
        }

        var desc = ""
        for (entry in pokemonKind.flavorTextEntries) {
            if (entry.language.name == "en") {
                desc = entry.flavorText.replace('\n', ' ')
                break
            }
        }

        return Pokemons(
            pokemonData.id,
            pokemonData.name,
            desc,
            types,
            stats,
            abilities,
            getEvolutionChain(evolutionChainData.chain))
    }

    private fun getEvolutionChain(chainDto: ChainDto): EvolutionChain {
        val list = chainDto.evolvesTo.map {
            getEvolutionChain(it)
        }

        var condition = ""
        if (chainDto.evolutionDetails.isNotEmpty()) {
            val evolutionDetailDto = chainDto.evolutionDetails[0]
            evolutionDetailDto.trigger?.let { condition += normalize(it.name) + " " }
            evolutionDetailDto.level?.let { condition += it }
            evolutionDetailDto.item?.let { condition += "\n${normalize(it.name)}" }
            evolutionDetailDto.timeOfDay?.let {
                if (it.isNotEmpty()) condition += "at $it"
            }
            evolutionDetailDto.heldItem?.let { condition += "\n Held ${normalize(it.name)}" }
            evolutionDetailDto.location?.let { condition += "\nIn ${normalize(it.name)}" }
            evolutionDetailDto.knownMoveType?.let { condition += "\nKnown ${normalize(it.name)} move" }
            evolutionDetailDto.knownMove?.let { condition += "\nKnown ${normalize(it.name)}" }
            evolutionDetailDto.happiness?.let { condition += "\nHappiness $it" }
            evolutionDetailDto.beauty?.let { condition += "\nBeauty $it" }
            evolutionDetailDto.affection?.let { condition += "\nAffection $it" }
            if (evolutionDetailDto.needsOverWorldRain) { condition += "\nNeeds over world rain" }
            evolutionDetailDto.gender?.let {
                condition += "\n${if (it == 0) "Male" else "Female"} gender"
            }
            evolutionDetailDto.relativePhysicalStats?.let {
                condition += when (it) {
                    0 -> {
                        "\nIf Attack = Defense"
                    }
                    1 -> {
                        "\nIf Attack > Defense"
                    }
                    else -> {
                        "\nIf Attack < Defense"
                    }
                }
            }
        }

        return EvolutionChain(
            StringUtils.getIdFromUrl(chainDto.species.url),
            chainDto.species.name.replaceFirstChar { c -> c.uppercase() },
            condition,
            list)
    }

    private fun normalize(string: String) =
        string.replace('-', ' ').replaceFirstChar { c -> c.uppercase() }
}