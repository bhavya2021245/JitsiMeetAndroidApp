package com.example.jitsimeet

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "favorite_users")
data class User(
    @PrimaryKey
    val name: String,
    val roomName: String)


val userList = listOf(
    User("Demo Purpose", "hello_buddy"),
    // Add more users as needed
)