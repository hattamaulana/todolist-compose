package com.github.hattamaulana.genesys.todo.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = -1,

    val title: String,
    val completed: Boolean = false
)