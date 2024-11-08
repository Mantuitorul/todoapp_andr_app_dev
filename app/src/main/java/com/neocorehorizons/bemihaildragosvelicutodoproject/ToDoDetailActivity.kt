package com.neocorehorizons.bemihaildragosvelicutodoproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.neocorehorizons.bemihaildragosvelicutodoproject.ui.theme.BemihaildragosvelicutodoprojectTheme
import com.neocorehorizons.bemihaildragosvelicutodoproject.util.MockupToDo
import com.neocorehorizons.bemihaildragosvelicutodoproject.ui.screens.ToDoDetailScreen

class ToDoDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the todo number from the intent
        // For now, we'll default to the first todo if none is provided
        val todoNumber = intent.getIntExtra("todo_number", 1)

        // Find the corresponding todo
        val todo = MockupToDo.getToDos().find { it.number == todoNumber }
            ?: MockupToDo.getToDos().first()

        setContent {
            BemihaildragosvelicutodoprojectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ToDoDetailScreen(toDo = todo)
                }
            }
        }
    }
}