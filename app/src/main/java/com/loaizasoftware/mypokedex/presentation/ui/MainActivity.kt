package com.loaizasoftware.mypokedex.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.loaizasoftware.mypokedex.presentation.mvi.PokemonIntent
import com.loaizasoftware.mypokedex.presentation.ui.general.theme.MyPokedexTheme
import com.loaizasoftware.mypokedex.presentation.ui.pokemon.ListPokemonScreen
import com.loaizasoftware.mypokedex.presentation.ui.pokemon.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint //This annotation allows hilt to provide dependencies in this activity
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<PokemonViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

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