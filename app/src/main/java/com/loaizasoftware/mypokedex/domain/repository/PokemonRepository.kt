package com.loaizasoftware.mypokedex.domain.repository

import com.loaizasoftware.mypokedex.domain.model.Pokemon

interface PokemonRepository {

    suspend fun getPokemonList(): List<Pokemon>

}