package com.lupa.pokeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lupa.pokeapp.mapper.PokemonToUIMapper
import com.lupa.pokeapp.model.PokemonDetail
import com.lupa.pokeapp.model.State
import com.lupa.pokeapp.network.NetworkState
import com.lupa.pokeapp.usecase.PokemonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonDetailUseCase: PokemonDetailUseCase
) : ViewModel() {

    private val mutableViewState = MutableLiveData<State<PokemonDetail?>>()

    fun getViewStateLiveData(): LiveData<State<PokemonDetail?>> {
        return mutableViewState
    }

    fun fetchPokemon(id: String) {
        mutableViewState.value = State.Loading

        viewModelScope.launch {
            when (val networkStatus = pokemonDetailUseCase.invoke(id)) {
                is NetworkState.Success -> {
                    mutableViewState.value =
                        State.Success(PokemonToUIMapper.map(networkStatus.data))
                }
                is NetworkState.Error -> {
                    val msg = networkStatus.error.message ?: "Error"
                    mutableViewState.value = State.Error(msg)
                }
            }
        }
    }
}
