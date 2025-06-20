package com.loaizasoftware.mypokedex.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.apollographql.apollo3.ApolloClient
import com.loaizasoftware.mypokedex.data.remote.GraphQLService
import com.loaizasoftware.mypokedex.data.remote.PokemonApi
import com.loaizasoftware.mypokedex.data.repository_impl.PokemonRepositoryImpl
import com.loaizasoftware.mypokedex.domain.usecase.GetPokemonListUseCase
import com.loaizasoftware.mypokedex.presentation.ui.general.UIState
import com.loaizasoftware.mypokedex.presentation.ui.general.theme.MyPokedexTheme
import com.loaizasoftware.mypokedex.presentation.ui.pokemon.PokemonViewModel

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: PokemonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val graphQLService = GraphQLService()
        val pokemonApi = PokemonApi(graphQLService.getApolloClient())
        val repo = PokemonRepositoryImpl(pokemonApi)
        val useCase = GetPokemonListUseCase(repo)
        viewModel = PokemonViewModel(useCase)

        /*setContent {
            MyPokedexTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                }
            }
        }*/
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyPokedexTheme {

    }
}