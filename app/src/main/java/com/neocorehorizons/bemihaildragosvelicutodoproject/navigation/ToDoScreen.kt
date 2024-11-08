package com.neocorehorizons.bemihaildragosvelicutodoproject.navigation

import androidx.annotation.StringRes
import com.neocorehorizons.bemihaildragosvelicutodoproject.R

enum class ToDoScreen(@StringRes val title: Int) {
    Login(title = R.string.app_name),
    TodoList(title = R.string.title_activity_list_to_dos),
    TodoAdd(title = R.string.add_edit_todo),
    TodoDetail(title = R.string.title_activity_edit_to_do)
}