package com.neocorehorizons.bemihaildragosvelicutodoproject.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.neocorehorizons.bemihaildragosvelicutodoproject.navigation.ToDoScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoAppBar(
    currentScreen: ToDoScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    showAddAction: Boolean,
    navigateToAddToDo: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = {
            if (showAddAction) {
                IconButton(onClick = navigateToAddToDo) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add Todo"
                    )
                }
            }
        }
    )
}