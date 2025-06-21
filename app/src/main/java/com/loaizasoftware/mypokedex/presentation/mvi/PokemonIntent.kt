package com.loaizasoftware.mypokedex.presentation.mvi

sealed interface PokemonIntent {
    data object LoadPokemons: PokemonIntent
}