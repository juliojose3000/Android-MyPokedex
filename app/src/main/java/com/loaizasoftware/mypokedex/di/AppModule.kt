package com.loaizasoftware.mypokedex.di

import com.apollographql.apollo3.ApolloClient
import com.loaizasoftware.mypokedex.data.remote.PokemonApi
import com.loaizasoftware.mypokedex.data.repository_impl.PokemonRepositoryImpl
import com.loaizasoftware.mypokedex.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module //A module provides dependencies
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {

        val SERVER_URL = "https://graphql-pokemon2.vercel.app/"

        val apolloClient = ApolloClient.Builder()
            .serverUrl(SERVER_URL)
            .build()

        return apolloClient

    }

    @Provides
    @Singleton
    fun providePokemonApi(apolloClient: ApolloClient): PokemonApi {
        return PokemonApi(apolloClient)
    }

    @Provides
    @Singleton
    fun providePokemonRepository(pokemonApi: PokemonApi): PokemonRepository {
        return PokemonRepositoryImpl(pokemonApi)
    }

}