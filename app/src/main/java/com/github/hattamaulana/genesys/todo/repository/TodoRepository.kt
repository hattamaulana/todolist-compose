package com.github.hattamaulana.genesys.todo.repository

import com.github.hattamaulana.genesys.todo.data.models.Todo
import com.github.hattamaulana.genesys.todo.data.sources.TodoDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface TodoRepository {
    fun getAllTodos(): Flow<List<Todo>>

    suspend fun addTodo(title: String)
    suspend fun updateTodoStatus(todo: Todo, isCompleted: Boolean)
    suspend fun deleteTodo(todo: Todo)
}

class TodoRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao
) : TodoRepository {

    override fun getAllTodos(): Flow<List<Todo>> {
        return todoDao.getAllTodos()
    }

    override suspend fun addTodo(title: String) {
        if (title.isNotBlank()) {
            todoDao.insertTodo(Todo(title = title))
        }
    }

    override suspend fun updateTodoStatus(todo: Todo, isCompleted: Boolean) {
        todoDao.updateTodo(todo.copy(completed = isCompleted))
    }

    override suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
    }
}