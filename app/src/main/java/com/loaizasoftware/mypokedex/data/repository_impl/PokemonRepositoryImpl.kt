package com.loaizasoftware.mypokedex.data.repository_impl

import com.loaizasoftware.mypokedex.domain.model.Pokemon
import com.loaizasoftware.mypokedex.data.remote.PokemonApi
import com.loaizasoftware.mypokedex.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(private val pokemonApi: PokemonApi): PokemonRepository {

    override suspend fun getPokemonList(): List<Pokemon> {
        return pokemonApi.fetchPokemons()
    }

}