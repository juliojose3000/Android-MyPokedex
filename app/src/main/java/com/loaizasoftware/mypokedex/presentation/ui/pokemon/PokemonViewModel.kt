package com.loaizasoftware.mypokedex.presentation.ui.pokemon

import android.util.Log
import com.loaizasoftware.mypokedex.domain.usecase.GetPokemonListUseCase
import com.loaizasoftware.mypokedex.presentation.mvi.PokemonIntent
import com.loaizasoftware.mypokedex.presentation.mvi.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class PokemonViewModel(private val getPokemonListUseCase: GetPokemonListUseCase) {

    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    private val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    private val _pokemonIntent = MutableSharedFlow<PokemonIntent>()
    private val pokemonIntent: SharedFlow<PokemonIntent> = _pokemonIntent.asSharedFlow()

    fun uiState() = uiState
    fun pokemonIntent() = pokemonIntent

    fun handleIntent(intent: PokemonIntent) {

        when(intent) {
            is PokemonIntent.LoadPokemons -> {
                fetchPokemonList()
            }
        }

    }

    private fun fetchPokemonList() {

        _uiState.value = UIState.Loading

        CoroutineScope(Dispatchers.IO).launch {

            try {
                getPokemonListUseCase.execute().also {
                    Log.v("MyTAG", it.toString())
                    _uiState.value = UIState.Success(it)
                }
            } catch (e: Exception) {
                Log.v("MyTAG", e.message.toString())
                _uiState.value = UIState.Error(e.message ?: "Unknown error")
            }

        }

    }

}

