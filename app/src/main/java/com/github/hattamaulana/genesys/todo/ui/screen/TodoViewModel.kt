package com.github.hattamaulana.genesys.todo.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.hattamaulana.genesys.todo.data.models.Todo
import com.github.hattamaulana.genesys.todo.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    val todoList: StateFlow<List<Todo>> = repository.getAllTodos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun addTodo(title: String) {
        viewModelScope.launch {
            repository.addTodo(title)
        }
    }

    fun update(todo: Todo, isCompleted: Boolean) {
        viewModelScope.launch {
            repository.updateTodoStatus(todo, isCompleted)
        }
    }

    fun delete(todo: Todo) {
        viewModelScope.launch {
            repository.deleteTodo(todo)
        }
    }
}