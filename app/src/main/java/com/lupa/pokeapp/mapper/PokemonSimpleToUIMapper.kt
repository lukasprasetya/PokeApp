package com.lupa.pokeapp.mapper

import com.lupa.pokeapp.model.Pokemon
import com.lupa.pokeapp.model.PokemonDetail
import com.lupa.pokeapp.model.PokemonSimple
import com.lupa.pokeapp.utils.PokemonUtils

object PokemonSimpleToUIMapper : BaseMapper<PokemonSimple, Pokemon> {
    override fun map(type: PokemonSimple): Pokemon {
        return Pokemon(
            type.id,
            type.name,
            type.type1,
            type.type2,
            PokemonUtils.getImageUrl(type.id),
            PokemonUtils.getPokemonTypeColor(type.type1)
        )
    }
}