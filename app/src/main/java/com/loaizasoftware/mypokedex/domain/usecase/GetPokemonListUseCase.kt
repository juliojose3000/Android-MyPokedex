package com.loaizasoftware.mypokedex.domain.usecase

import com.loaizasoftware.mypokedex.domain.model.Pokemon
import com.loaizasoftware.mypokedex.domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(private val repository: PokemonRepository) {

    suspend fun execute(): List<Pokemon> {

        return repository.getPokemonList()

    }

}