package com.loaizasoftware.mypokedex.domain.usecase

import com.loaizasoftware.mypokedex.domain.model.Pokemon
import com.loaizasoftware.mypokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonListUseCase(private val repository: PokemonRepository) {

    suspend fun execute(): List<Pokemon> {

        return repository.getPokemonList()

    }

}