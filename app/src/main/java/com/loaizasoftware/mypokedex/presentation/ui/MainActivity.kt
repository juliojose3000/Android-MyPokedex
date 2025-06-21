package com.loaizasoftware.mypokedex.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.loaizasoftware.mypokedex.data.remote.GraphQLService
import com.loaizasoftware.mypokedex.data.remote.PokemonApi
import com.loaizasoftware.mypokedex.data.repository_impl.PokemonRepositoryImpl
import com.loaizasoftware.mypokedex.domain.usecase.GetPokemonListUseCase
import com.loaizasoftware.mypokedex.presentation.mvi.PokemonIntent
import com.loaizasoftware.mypokedex.presentation.ui.general.theme.MyPokedexTheme
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

            //This avoid to call multiple times the API when recomposition occurs
            LaunchedEffect(Unit) {
                viewModel.handleIntent(PokemonIntent.LoadPokemons)
            }

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