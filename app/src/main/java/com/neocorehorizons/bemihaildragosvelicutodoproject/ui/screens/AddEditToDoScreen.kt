package com.neocorehorizons.bemihaildragosvelicutodoproject.ui.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.neocorehorizons.bemihaildragosvelicutodoproject.data.MyConfiguration
import com.neocorehorizons.bemihaildragosvelicutodoproject.data.TodoUiState
import com.neocorehorizons.bemihaildragosvelicutodoproject.model.User
import com.neocorehorizons.bemihaildragosvelicutodoproject.util.MockupUser
import androidx.lifecycle.viewmodel.compose.viewModel
import be.hostens.mytodoapp.utils.ToDoImage
import com.neocorehorizons.bemihaildragosvelicutodoproject.ui.theme.TealButtonColor


@Composable
fun AddEditToDoScreen(
    modifier: Modifier = Modifier,
    viewModel: AddEditToDoViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Header with Logo and Title
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),

            ) {
                ToDoImage(
                    modifier = Modifier
                        .size(96.dp)
                        .background(Color.White)
                )

                Text(
                    text = "Add ToDo",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            // Status Row with red divider
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "#NEW",
                    modifier = Modifier.padding(end = 8.dp),
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.titleMedium

                )
                Text(
                    text = "NEW",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Divider(
                color = Color.Red,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        // Created By Text
        Text(
            text = "Created by ${MyConfiguration.loggedInUser?.firstName ?: "Dragos"} ${MyConfiguration.loggedInUser?.lastName ?: "Velicu"}",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Title TextField
        OutlinedTextField(
            value = uiState.title,
            onValueChange = { viewModel.updateTitle(it) },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Description TextField
        OutlinedTextField(
            value = uiState.description,
            onValueChange = { viewModel.updateDescription(it) },
            label = { Text("Description") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Time Estimated TextField
        OutlinedTextField(
            value = uiState.timeEstimated,
            onValueChange = { viewModel.updateTimeEstimated(it) },
            label = { Text("Time estimated (hours)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Assigned User Section
        Text("Select assigned user.")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { viewModel.toggleUserDialog(true) },
                modifier = Modifier.weight(0.5f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = TealButtonColor
                )
            ) {
                Text("Assign user")
            }

            Button(
                onClick = { viewModel.updateAssignedUser(null) },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = TealButtonColor
                )
            ) {
                Text("Remove assigned user")
            }
        }

        if (uiState.showUserDialog) {
            UserSelectionDialog(
                onDismiss = { viewModel.toggleUserDialog(false) },
                onUserSelected = { user ->
                    viewModel.updateAssignedUser(user)
                    viewModel.toggleUserDialog(false)
                }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Status Switches
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatusSwitch(
                    text = "Analysis done?",
                    checked = uiState.analysisDone,
                    onCheckedChange = { viewModel.updateAnalysisDone(it) }
                )
                StatusSwitch(
                    text = "Development done?",
                    checked = uiState.developmentDone,
                    onCheckedChange = { viewModel.updateDevelopmentDone(it) }
                )
                StatusSwitch(
                    text = "Review & testing done?",
                    checked = uiState.reviewAndTestingDone,
                    onCheckedChange = { viewModel.updateReviewAndTestingDone(it) }
                )
                StatusSwitch(
                    text = "Acceptance done?",
                    checked = uiState.acceptanceDone,
                    onCheckedChange = { viewModel.updateAcceptanceDone(it) }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Time Remaining Display
        Text(
            text = "Time remaining ${calculateTimeRemaining(uiState)} hours",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Action Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { /* Handle Cancel */ },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = TealButtonColor
                )
            ) {
                Text("Cancel")
            }
            Button(
                onClick = { /* Handle Save */ },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = TealButtonColor
                )
            ) {
                Text("Save")
            }
        }
    }
}

@Composable
fun StatusSwitch(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text)
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
fun UserSelectionDialog(
    onDismiss: () -> Unit,
    onUserSelected: (User) -> Unit
) {
    val users = MockupUser.getUsers()
    val TealButtonColor = Color(0xFF00838F)

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = Color.LightGray,
        title = {
            Text(
                text = "Select user",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                users.forEach { user ->
                    Button(
                        onClick = { onUserSelected(user) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = TealButtonColor
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "${user.firstName} ${user.lastName}",
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }
        },
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier.weight(1f),
                    border = BorderStroke(1.dp, Color.Gray),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.White
                    )
                ) {
                    Text("OK", color = Color.Black)
                }
                Button(
                    onClick = onDismiss,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = TealButtonColor
                    )
                ) {
                    Text("Cancel")
                }
            }
        },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    )
}

private fun calculateTimeRemaining(uiState: TodoUiState): Double {
    val totalTime = uiState.timeEstimated.toDoubleOrNull() ?: 0.0
    return when {
        uiState.acceptanceDone -> 0.0
        uiState.reviewAndTestingDone -> totalTime * 0.10
        uiState.developmentDone -> totalTime * 0.30
        uiState.analysisDone -> totalTime * 0.85
        else -> totalTime
    }
}