package com.lupa.pokeapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EvolutionChainData(
    val chain: ChainDto
) : Parcelable

@Parcelize
data class ChainDto(
    val species: Resource,
    @SerializedName("evolves_to") val evolvesTo: List<ChainDto>,
    @SerializedName("evolution_details") val evolutionDetails: List<EvolutionData>
) : Parcelable
