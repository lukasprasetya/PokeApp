package com.lupa.pokeapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lupa.pokeapp.fragment.EvolutionsFragment
import com.lupa.pokeapp.fragment.StatsFragment
import com.lupa.pokeapp.model.PokemonDetail

class PokemonPagerAdapter(
    fa: FragmentActivity,
    private val pokemonDetail: PokemonDetail
): FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                StatsFragment(pokemonDetail.stats, pokemonDetail.color)
            }
            else -> {
                EvolutionsFragment(pokemonDetail.evolutions)
            }
        }
    }
}