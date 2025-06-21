package com.loaizasoftware.mypokedex.presentation.ui.pokemon

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.loaizasoftware.mypokedex.domain.model.Pokemon
import com.loaizasoftware.mypokedex.presentation.mvi.UIState
import com.loaizasoftware.mypokedex.utils.ext.showToast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPokemonScreen(viewModel: PokemonViewModel) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Pokemons") }
            )
        }
    ) { paddingValues ->

        val uiState = viewModel.uiState().collectAsState()
        val context = LocalContext.current

        Column(modifier = Modifier.padding(paddingValues)) {

            when(uiState.value) {

                is UIState.Loading -> {
                    ShowLoader()
                }

                is UIState.Success -> {
                    ListPokemon((uiState.value as UIState.Success).data as List<Pokemon>)
                }

                is UIState.Error -> {
                    val message = (uiState.value as UIState.Error).message
                    context showToast message
                }

            }

        }

    }

}


@Composable
fun ShowLoader() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        CircularProgressIndicator(
            modifier = Modifier
                .align(alignment = Alignment.Center),
            color = Color.Blue
        )

    }

}

@Composable
fun ListPokemon(list: List<Pokemon>) {

    LazyColumn(modifier = Modifier.padding(16.dp)) {

        items(list.size) {

            Text(text = list[it].name)

        }

    }

}