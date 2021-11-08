package com.mokelab.compose.todo.ui.main

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.mokelab.compose.todo.Greeting

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel,
) {
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background) {
        Greeting("Android")
    }
}