package com.github.hattamaulana.genesys.todo.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.hattamaulana.genesys.todo.data.models.Todo
import com.github.hattamaulana.genesys.todo.ui.components.TodoItem

@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    onTodoClick: (Int) -> Unit,
    viewModel: TodoViewModel = hiltViewModel()
) {
    val todoList by viewModel.todoList.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Todo List") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TodoInputForm(
                onAddTodo = { title ->
                    viewModel.addTodo(title)
                }
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(todoList.size) {
                    val todo = todoList.get(it)
                    TodoItem(
                        todo = todo,
                        onToggleComplete = {
                            viewModel.update(todo, !todo.completed)
                        },
                        onDelete = {
                            viewModel.delete(todo)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TodoInputForm(
    onAddTodo: (String) -> Unit
) {
    var textState by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = textState,
            onValueChange = { textState = it },
            modifier = Modifier.weight(1f),
            label = { Text("Add new todo...") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (textState.isNotBlank()) {
                        onAddTodo(textState)
                        textState = "" // Clear input after adding
                    }
                }
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = {
                if (textState.isNotBlank()) {
                    onAddTodo(textState)
                    textState = "" // Clear input after adding
                }
            },
            modifier = Modifier.height(56.dp) // Match height with OutlinedTextField
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            Spacer(modifier = Modifier.width(4.dp))
            Text("Add")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoList(
    todos: List<Todo>,
    onTodoClick: (Int) -> Unit,
    onToggleComplete: (Int) -> Unit,
    onDelete: (Int) -> Unit
) {
    LazyColumn {
        items(count = todos.size) {
            val todo = todos[it]
            TodoItem(
                todo = todo,
                onToggleComplete = onToggleComplete,
                onDelete = onDelete
            )
        }
    }
}