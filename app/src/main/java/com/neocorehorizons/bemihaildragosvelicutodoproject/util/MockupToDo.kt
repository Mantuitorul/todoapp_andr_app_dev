package com.neocorehorizons.bemihaildragosvelicutodoproject.util

import ToDo
import com.neocorehorizons.bemihaildragosvelicutodoproject.model.User
import java.util.Date

class MockupToDo {
    companion object {
        private val assignedUser = User(2, "DirkHostens", "Dirk", "Hostens", "password", true)
        private val createduser = User(1, "FrankDebaere", "Frank", "Debaere", "password", true)

        private val toDos = mutableListOf(
            ToDo(
                1,
                "Finish detail ToDo",
                "Add extra fields like assigned user, time estimated, ...to the ToDo detail screen",
                createduser,
                Date(),
                assignedUser,
                Date(),
                20
            ),
            ToDo(
                2,
                "Delete user",
                "In the app you must add the possibility to delete a user from the list.",
                createduser,
                Date(),
                null,
                null,
                50
            ),
            ToDo(
                3,
                "Improve performance",
                "Getting data from the API is slow and must be optimized.",
                createduser,
                Date(),
                assignedUser,
                null,
                60
            )
        )

        fun getToDos(): List<ToDo> {
            return toDos.toList()
        }

        fun addTodo(todo: ToDo) {
            toDos.add(todo)
        }

        fun getNextTodoNumber(): Int {
            return toDos.maxOfOrNull { it.number }?.plus(1) ?: 1
        }
    }
}