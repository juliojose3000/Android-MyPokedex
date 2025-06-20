package com.loaizasoftware.mypokedex.data.remote

import com.loaizasoftware.mypokedex.data.model.Pokemon

class PokemonApi(private val service: GraphQLService) {

    suspend fun fetchPokemons(): List<Pokemon> {
        val query = GetPokemonsQuery()
        val response = service.getPokemonList().query(query).execute()
        return response.data?.pokemons?.map{ it.n} ?: emptyList()
    }

}