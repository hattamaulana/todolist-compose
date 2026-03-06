package com.github.hattamaulana.genesys.todo.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.hattamaulana.genesys.todo.ui.screen.TodoListScreen

@Composable
fun TodoNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "todoList") {
        composable("todoList") {
            TodoListScreen(onTodoClick = {
                navController.navigate("todoDetail/$it")
            })
        }
    }
}