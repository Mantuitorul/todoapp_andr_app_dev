package com.neocorehorizons.bemihaildragosvelicutodoproject

import ToDo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import com.neocorehorizons.bemihaildragosvelicutodoproject.ui.screens.AddEditToDoScreen
import com.neocorehorizons.bemihaildragosvelicutodoproject.ui.screens.ToDoDetailScreen
import com.neocorehorizons.bemihaildragosvelicutodoproject.ui.theme.BemihaildragosvelicutodoprojectTheme
import com.neocorehorizons.bemihaildragosvelicutodoproject.util.MockupToDo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toDos = MockupToDo.getToDos()
        val myToDo: ToDo = toDos[0]
        setContent {
            BemihaildragosvelicutodoprojectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //ToDoDetailScreen(toDo = myToDo)
                    AddEditToDoScreen()
                }
            }
        }
    }
}
