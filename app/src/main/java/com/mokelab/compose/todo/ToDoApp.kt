package com.mokelab.compose.todo

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mokelab.compose.todo.ui.theme.ComposeToDoTheme

@Composable
fun ToDoApp() {
    val navController = rememberNavController()

    ComposeToDoTheme {
        NavHost(navController = navController, startDestination = "main") {
            composable("main") {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
    }
}