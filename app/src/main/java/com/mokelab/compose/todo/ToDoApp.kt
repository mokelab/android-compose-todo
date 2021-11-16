package com.mokelab.compose.todo

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavArgs
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mokelab.compose.todo.ui.create.CreateToDoScreen
import com.mokelab.compose.todo.ui.create.CreateToDoViewModel
import com.mokelab.compose.todo.ui.detail.ToDoDetailScreen
import com.mokelab.compose.todo.ui.detail.ToDoDetailViewModel
import com.mokelab.compose.todo.ui.edit.EditToDoScreen
import com.mokelab.compose.todo.ui.edit.EditToDoViewModel
import com.mokelab.compose.todo.ui.main.MainScreen
import com.mokelab.compose.todo.ui.main.MainViewModel
import com.mokelab.compose.todo.ui.theme.ComposeToDoTheme

@Composable
fun ToDoApp() {
    val navController = rememberNavController()

    ComposeToDoTheme {
        NavHost(navController = navController, startDestination = "main") {
            composable("main") {
                val viewModel = hiltViewModel<MainViewModel>()
                MainScreen(navController = navController, viewModel = viewModel)
            }
            composable("create") {
                val viewModel = hiltViewModel<CreateToDoViewModel>()
                CreateToDoScreen(navController = navController, viewModel = viewModel)
            }
            composable(
                "detail/{todoId}",
                arguments = listOf(navArgument("todoId") { type = NavType.IntType })
            ) { backStackEntry ->
                val viewModel = hiltViewModel<ToDoDetailViewModel>()
                val todoId = backStackEntry.arguments?.getInt("todoId") ?: 0
                viewModel.setId(todoId)
                ToDoDetailScreen(
                    navController = navController,
                    viewModel = viewModel,
                )
            }
            composable(
                "edit/{todoId}",
                arguments = listOf(navArgument("todoId") { type = NavType.IntType })
            ) { backStackEntry ->
                val viewModel = hiltViewModel<EditToDoViewModel>()
                val todoId = backStackEntry.arguments?.getInt("todoId") ?: 0
                EditToDoScreen(
                    navController = navController,
                    viewModel = viewModel,
                    todoId = todoId,
                )
            }
        }
    }
}