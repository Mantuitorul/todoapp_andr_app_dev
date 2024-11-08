package com.neocorehorizons.bemihaildragosvelicutodoproject

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.neocorehorizons.bemihaildragosvelicutodoproject.navigation.ToDoScreen
import com.neocorehorizons.bemihaildragosvelicutodoproject.ui.navigation.ToDoAppBar
import com.neocorehorizons.bemihaildragosvelicutodoproject.ui.screens.*

@Composable
fun ToDoApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ToDoScreen.valueOf(
        backStackEntry?.destination?.route ?: ToDoScreen.Login.name
    )

    var showAddAction by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            ToDoAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                showAddAction = showAddAction,
                navigateToAddToDo = { navController.navigate(ToDoScreen.TodoAdd.name) }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ToDoScreen.Login.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = ToDoScreen.Login.name) {
                showAddAction = false
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate(ToDoScreen.TodoList.name) {
                            popUpTo(ToDoScreen.Login.name) { inclusive = true }
                        }
                    }
                )
            }

            composable(route = ToDoScreen.TodoList.name) {
                showAddAction = true
                ToDoListScreen(
                    onToDoClick = { todo ->
                        navController.navigate("${ToDoScreen.TodoDetail.name}/${todo.number}")
                    }
                )
            }

            composable(route = ToDoScreen.TodoAdd.name) {
                showAddAction = false
                AddEditToDoScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable(
                route = "${ToDoScreen.TodoDetail.name}/{todoId}"
            ) {
                showAddAction = false
                // Implementation for detail screen
            }
        }
    }
}