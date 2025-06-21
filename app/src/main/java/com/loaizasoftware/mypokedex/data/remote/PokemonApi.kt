package com.loaizasoftware.mypokedex.data.remote

import com.apollographql.apollo3.ApolloClient
import com.loaizasoftware.mypokedex.GetPokemonsQuery
import com.loaizasoftware.mypokedex.domain.model.Pokemon
import javax.inject.Inject

class PokemonApi @Inject constructor(private val apolloClient: ApolloClient) {

    suspend fun fetchPokemons(): List<Pokemon> {

        // 1. Create ina instance of the Query class
        val query = GetPokemonsQuery(first = 10)

        // 2. Execute the query using the ApolloClient
        val response = apolloClient.query(query).execute()

        // 3. Check for GraphQL errors
        if (response.hasErrors()) {
            response.errors?.forEach { error ->
                // Log error.message, error.locations, error.extensions
                println("GraphQL Error: ${error.message}")
            }
            return emptyList() // Or throw a custom exception
        }


        // 4. Process the data
        //    The path to your data depends on your query structure and generated models
        //    Replace 'actualPokemonListName' with the correct list field from your response data.
        //    Apollo generates classes matching your query structure.
        val pokemonDataList = response.data?.pokemons

        return pokemonDataList?.mapNotNull { pokemonData ->
            // Ensure pokemonData and its name are not null
            pokemonData?.name?.let { name ->
                Pokemon(name = name /*, other properties */)
            }
        } ?: emptyList()


    }

}