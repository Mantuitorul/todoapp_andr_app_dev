package com.neocorehorizons.bemihaildragosvelicutodoproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.neocorehorizons.bemihaildragosvelicutodoproject.ui.screens.ToDoListScreen
import com.neocorehorizons.bemihaildragosvelicutodoproject.ui.theme.BemihaildragosvelicutodoprojectTheme

class ToDoListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BemihaildragosvelicutodoprojectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ToDoListScreen()
                }
            }
        }
    }
}


