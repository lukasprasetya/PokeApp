package com.lupa.pokeapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PokemonKind(
    val id: String = "",
    val name: String = "",
    val color: Resource,
    @SerializedName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntry>,
    @SerializedName("is_legendary") val isLegendary: Boolean = false,
    val generation: Resource,
    @SerializedName("evolution_chain") val evolutionChain: Resource
) : Parcelable