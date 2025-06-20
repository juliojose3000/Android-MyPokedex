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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.apollographql.apollo3.ApolloClient
import com.loaizasoftware.mypokedex.data.remote.GraphQLService
import com.loaizasoftware.mypokedex.data.remote.PokemonApi
import com.loaizasoftware.mypokedex.data.repository_impl.PokemonRepositoryImpl
import com.loaizasoftware.mypokedex.domain.usecase.GetPokemonListUseCase
import com.loaizasoftware.mypokedex.presentation.ui.general.UIState
import com.loaizasoftware.mypokedex.presentation.ui.general.theme.MyPokedexTheme
import com.loaizasoftware.mypokedex.presentation.ui.pokemon.ListPokemon
import com.loaizasoftware.mypokedex.presentation.ui.pokemon.ListPokemonScreen
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

        setContent {
            MyPokedexTheme {
                NavigationComposable(viewModel)
            }
        }
    }
}

@Composable
fun NavigationComposable(viewModel: PokemonViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "list_pokemon") {

        composable("list_pokemon") {

            ListPokemonScreen(viewModel = viewModel)

        }

    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyPokedexTheme {

    }
}