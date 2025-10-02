package com.example.bookify

object AppData {
    data class User(val name: String, val email: String, val password: String)

    // Demo user log in
    // email: user@gmail.com
    // password: 123456
    val users = mutableListOf(
        User("Demo User", "user@gmail.com", "123456")
    )

    var currentUser: User? = null

    // favourites (book ids, e.g., "bk_1")
    val favourites: MutableSet<String> = linkedSetOf()
}
