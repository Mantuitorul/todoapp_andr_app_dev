package com.neocorehorizons.bemihaildragosvelicutodoproject.model

data class User(
    val id: Int,
    val userName: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val isActive: Boolean
) {
    constructor() : this(0, "", "", "", "", true)
}
