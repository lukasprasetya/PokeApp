package com.lupa.pokeapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lupa.pokeapp.mapper.PokemonSimpleToUIMapper
import com.lupa.pokeapp.model.Pokemon
import com.lupa.pokeapp.model.State
import com.lupa.pokeapp.network.NetworkState
import com.lupa.pokeapp.usecase.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonListUseCase: PokemonUseCase
) : ViewModel() {

    private val mutableViewState = MutableLiveData<State<List<Pokemon>?>>()

    fun getViewStateLiveData(): LiveData<State<List<Pokemon>?>> {
        return mutableViewState
    }

    fun fetchPokemons() {
        mutableViewState.value = State.Loading

        viewModelScope.launch {
            when (val networkStatus = pokemonListUseCase.invoke()) {
                is NetworkState.Success -> {
                    mutableViewState.value = State.Success(networkStatus.data.map {
                        PokemonSimpleToUIMapper.map(it)
                    })
                }
                is NetworkState.Error -> {
                    val msg = networkStatus.error.message ?: "Error"
                    mutableViewState.value = State.Error(msg)
                }
            }
        }
    }
}