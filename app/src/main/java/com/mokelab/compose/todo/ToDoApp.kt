package com.mokelab.compose.todo

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.mokelab.compose.todo.ui.theme.ComposeToDoTheme

@Composable
fun ToDoApp() {
    ComposeToDoTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            Greeting("Android")
        }
    }
}