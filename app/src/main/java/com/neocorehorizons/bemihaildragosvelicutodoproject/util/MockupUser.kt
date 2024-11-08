package com.neocorehorizons.bemihaildragosvelicutodoproject.util

import com.neocorehorizons.bemihaildragosvelicutodoproject.model.User

class MockupUser {
    companion object {
        private val user1 = User(2, "DirkHostens", "Dirk","Hostens", "password", true)
        private val user2 = User(1, "FrankDebare", "Frank","Debaere", "password", true)
        private val users = arrayOf(user1, user2)

        fun getUser(userName: String?, password: String?) : User? {
            var user : User? = null
            var filterUsers = users.filter {
                it.userName.lowercase().equals(userName?.lowercase()) &&  it.password.equals(password)
            }
            if (!filterUsers.isEmpty()) {
                user = filterUsers[0]
            }
            return user
        }

        fun getUsers() : List<User> {
            return users.toList()
        }
    }


}