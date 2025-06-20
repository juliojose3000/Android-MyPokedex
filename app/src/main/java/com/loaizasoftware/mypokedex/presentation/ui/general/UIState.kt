package com.loaizasoftware.mypokedex.presentation.ui.general

sealed class UIState {
    data object Loading: UIState()
    data class Success(val data: Any): UIState()
    data class Error(val message: String): UIState()
}