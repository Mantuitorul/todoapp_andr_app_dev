package com.neocorehorizons.bemihaildragosvelicutodoproject.data

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
