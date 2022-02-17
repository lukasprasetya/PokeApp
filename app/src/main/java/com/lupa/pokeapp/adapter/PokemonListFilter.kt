package com.lupa.pokeapp.adapter

import android.widget.Filter
import com.lupa.pokeapp.model.Pokemon

class PokemonListFilter(private val adapter: PokemonAdapter): Filter() {

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        val filterString = constraint.toString()
        val results = FilterResults()
        val list = adapter.dataset

        val filteredList: List<Pokemon> = if (filterString.isEmpty()) {
            list
        } else {
            list.filter {
                it.name.contains(filterString, true) or (it.id == filterString)
            }
        }

        results.values = filteredList
        results.count = filteredList.size

        return results
    }

    @Suppress("UNCHECKED_CAST")
    override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
        adapter.filteredDataset = results?.values as List<Pokemon>
        adapter.notifyDataSetChanged()
    }
}