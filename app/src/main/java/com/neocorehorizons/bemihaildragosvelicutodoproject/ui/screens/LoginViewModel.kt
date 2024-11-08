package com.neocorehorizons.bemihaildragosvelicutodoproject.ui.screens

import androidx.lifecycle.ViewModel
import com.neocorehorizons.bemihaildragosvelicutodoproject.data.LoginUiState
import com.neocorehorizons.bemihaildragosvelicutodoproject.data.MyConfiguration
import com.neocorehorizons.bemihaildragosvelicutodoproject.util.MockupUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun updateUsername(username: String) {
        _uiState.update { it.copy(username = username) }
    }

    fun updatePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun login(): Boolean {
        _uiState.update { it.copy(isLoading = true, error = null) }

        val user = MockupUser.getUser(uiState.value.username, uiState.value.password)

        return if (user != null) {
            MyConfiguration.loggedInUser = user
            _uiState.update { it.copy(isLoading = false) }
            true
        } else {
            _uiState.update { it.copy(
                isLoading = false,
                error = "Invalid username or password"
            )}
            false
        }
    }
}
