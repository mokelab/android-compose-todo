package com.mokelab.compose.todo

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavArgs
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mokelab.compose.todo.ui.create.CreateToDoScreen
import com.mokelab.compose.todo.ui.detail.ToDoDetailScreen
import com.mokelab.compose.todo.ui.edit.EditToDoScreen
import com.mokelab.compose.todo.ui.main.MainScreen
import com.mokelab.compose.todo.ui.theme.ComposeToDoTheme

@Composable
fun ToDoApp() {
    val navController = rememberNavController()

    ComposeToDoTheme {
        NavHost(navController = navController, startDestination = "main") {
            composable("main") {
                MainScreen(navController = navController)
            }
            composable("create") {
                CreateToDoScreen(navController = navController)
            }
            composable(
                "detail/{todoId}",
                arguments = listOf(navArgument("todoId") { type = NavType.IntType })
            ) { backStackEntry ->
                val todoId = backStackEntry.arguments?.getInt("todoId") ?: 0
                ToDoDetailScreen(navController = navController, todoId = todoId)
            }
            composable(
                "edit/{todoId}",
                arguments = listOf(navArgument("todoId") { type = NavType.IntType })
            ) { backStackEntry ->
                val todoId = backStackEntry.arguments?.getInt("todoId") ?: 0
                EditToDoScreen(navController = navController, todoId = todoId)
            }
        }
    }
}