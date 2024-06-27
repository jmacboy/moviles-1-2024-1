package com.example.practicanavegacion.repositories

import com.example.practicanavegacion.models.User

object FakeUsersRepository {
    private val users = mutableListOf<User>(
        User(1, "Admin", "admin@admin.com", "admin", true),
        User(2, "Juan Perez", "juan@test.com", "1234", false),
        User(3, "María Perez", "maria@test.com", "1234", false),
        User(4, "Perico de los Palotes", "perico@test.com", "1234", false),
        User(5, "Pepe Pecas", "pepecas@test.com", "1234", false),
        User(5, "Admin 2", "admin2@admin.com", "admin2", true),
    )


    fun loginClient(email: String, password: String): User? {
        return users.find {
            it.email == email && it.password == password && !it.isAdmin
        }
    }

    fun loginAdmin(email: String, password: String): User? {
        return users.find {
            it.email == email && it.password == password && it.isAdmin
        }
    }
}