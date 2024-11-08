package com.neocorehorizons.bemihaildragosvelicutodoproject.data

import com.neocorehorizons.bemihaildragosvelicutodoproject.model.User


data class TodoUiState(
    val number: Int = 0,
    val title: String = "",
    val description: String = "",
    val assignedUser: User? = null,
    val timeEstimated: String = "",
    val analysisDone: Boolean = false,
    val developmentDone: Boolean = false,
    val reviewAndTestingDone: Boolean = false,
    val acceptanceDone: Boolean = false,
    val showUserDialog: Boolean = false,
    val status: String = "NEW"
)