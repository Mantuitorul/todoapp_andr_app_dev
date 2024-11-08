package com.neocorehorizons.bemihaildragosvelicutodoproject.ui.screens

import ToDo
import androidx.lifecycle.ViewModel
import com.neocorehorizons.bemihaildragosvelicutodoproject.data.MyConfiguration
import com.neocorehorizons.bemihaildragosvelicutodoproject.data.TodoUiState
import com.neocorehorizons.bemihaildragosvelicutodoproject.model.User
import com.neocorehorizons.bemihaildragosvelicutodoproject.util.MockupToDo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Date

class AddEditToDoViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TodoUiState())
    val uiState: StateFlow<TodoUiState> = _uiState.asStateFlow()

    fun updateTitle(title: String) {
        _uiState.update { it.copy(title = title) }
    }

    fun updateDescription(description: String) {
        _uiState.update { it.copy(description = description) }
    }

    fun updateTimeEstimated(time: String) {
        _uiState.update { it.copy(timeEstimated = time) }
    }

    fun updateAssignedUser(user: User?) {
        _uiState.update { it.copy(assignedUser = user) }
    }

    fun toggleUserDialog(show: Boolean) {
        _uiState.update { it.copy(showUserDialog = show) }
    }

    fun updateAnalysisDone(done: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                analysisDone = done,
                developmentDone = if (!done) false else currentState.developmentDone,
                reviewAndTestingDone = if (!done) false else currentState.reviewAndTestingDone,
                acceptanceDone = if (!done) false else currentState.acceptanceDone
            )
        }
    }

    fun updateDevelopmentDone(done: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                analysisDone = if (done) true else currentState.analysisDone,
                developmentDone = done,
                reviewAndTestingDone = if (!done) false else currentState.reviewAndTestingDone,
                acceptanceDone = if (!done) false else currentState.acceptanceDone
            )
        }
    }

    fun updateReviewAndTestingDone(done: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                analysisDone = if (done) true else currentState.analysisDone,
                developmentDone = if (done) true else currentState.developmentDone,
                reviewAndTestingDone = done,
                acceptanceDone = if (!done) false else currentState.acceptanceDone
            )
        }
    }

    fun updateAcceptanceDone(done: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                analysisDone = if (done) true else currentState.analysisDone,
                developmentDone = if (done) true else currentState.developmentDone,
                reviewAndTestingDone = if (done) true else currentState.reviewAndTestingDone,
                acceptanceDone = done
            )
        }
    }

    fun saveTodo() {
        val currentState = uiState.value
        val timeEstimated = currentState.timeEstimated.toIntOrNull() ?: 0

        val newTodo = ToDo(
            number = MockupToDo.getNextTodoNumber(),  // We'll need to add this function
            title = currentState.title,
            description = currentState.description,
            createdByUser = MyConfiguration.loggedInUser ?: User(),
            createdOnDate = Date(),
            assignedToUser = currentState.assignedUser,
            finishedOnDate = if (currentState.acceptanceDone) Date() else null,
            timeEstimated = timeEstimated,
            analysisDone = currentState.analysisDone,
            developmentDone = currentState.developmentDone,
            reviewAndTestingDone = currentState.reviewAndTestingDone,
            acceptanceDone = currentState.acceptanceDone
        )

        MockupToDo.addTodo(newTodo)  // We'll need to add this function
    }
}