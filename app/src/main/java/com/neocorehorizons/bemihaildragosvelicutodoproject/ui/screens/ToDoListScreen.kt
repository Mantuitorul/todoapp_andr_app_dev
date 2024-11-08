package com.neocorehorizons.bemihaildragosvelicutodoproject.ui.screens

import ToDo
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import be.hostens.mytodoapp.utils.ToDoImage
import com.neocorehorizons.bemihaildragosvelicutodoproject.ui.theme.BemihaildragosvelicutodoprojectTheme
import com.neocorehorizons.bemihaildragosvelicutodoproject.util.MockupToDo

@Composable
fun ToDoListScreen(
    modifier: Modifier = Modifier,
    onToDoClick: (ToDo) -> Unit = {}
) {
    val todos = remember { MockupToDo.getToDos() }

    Column(modifier = modifier.fillMaxSize()) {
        ToDoListHeader()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(todos) { todo ->
                ToDoCard(
                    todo = todo,
                    onClick = { onToDoClick(todo) }
                )
            }
        }
    }
}

@Composable
private fun ToDoListHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ToDoImage(modifier = Modifier.padding(bottom = 16.dp))
        Text(
            text = "My ToDos",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Divider(
            modifier = Modifier.padding(top = 8.dp),
            color = MaterialTheme.colorScheme.primary,
            thickness = 2.dp
        )
    }
}

@Composable
private fun ToDoCard(
    todo: ToDo,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "#${todo.number}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = todo.statusDescription,
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Cursive,
                    color = when(todo.status) {
                        ToDo.Status.NEW -> MaterialTheme.colorScheme.primary
                        ToDo.Status.ASSIGNED -> MaterialTheme.colorScheme.secondary
                        ToDo.Status.FINISHED -> MaterialTheme.colorScheme.tertiary
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = todo.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = todo.description,
                fontSize = 14.sp,
                maxLines = 2
            )

            if (todo.assignedToUser != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Assigned to: ${todo.assignedToUser.firstName} ${todo.assignedToUser.lastName}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            LinearProgressIndicator(
                progress = ((todo.timeEstimated - todo.timeRemaining) / todo.timeEstimated).toFloat(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ToDoListScreenPreview() {
    BemihaildragosvelicutodoprojectTheme {
        ToDoListScreen()
    }
}

@Preview(showBackground = true)
@Composable
private fun ToDoCardPreview() {
    val sampleToDo = MockupToDo.getToDos().first()
    BemihaildragosvelicutodoprojectTheme {
        ToDoCard(
            todo = sampleToDo,
            onClick = {}
        )
    }
}

