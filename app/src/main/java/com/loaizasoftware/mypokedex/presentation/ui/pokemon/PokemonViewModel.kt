package com.loaizasoftware.mypokedex.presentation.ui.pokemon

import android.util.Log
import com.loaizasoftware.mypokedex.domain.usecase.GetPokemonListUseCase
import com.loaizasoftware.mypokedex.presentation.ui.general.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonViewModel(private val getPokemonListUseCase: GetPokemonListUseCase) {

    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    private val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    fun getUiState() = uiState

    init {
        fetchPokemonList()
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

