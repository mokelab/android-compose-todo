package com.mokelab.compose.todo.ui.create

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.mokelab.compose.todo.R
import com.mokelab.compose.todo.ui.main.MainScreen
import com.mokelab.compose.todo.ui.main.MainViewModel
import com.mokelab.compose.todo.ui.theme.ComposeToDoTheme

@Composable
fun CreateToDoScreen(
    navController: NavController,
    viewModel: CreateToDoViewModel,
) {
    val title = remember { mutableStateOf("") }
    val detail = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CreateTopBar(navController) {
                viewModel.save(title.value, detail.value)
            }
        },
    ) {
        CreateToDoBody(title, detail)
    }
}

@Composable
fun CreateTopBar(navController: NavController, save: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(Icons.Filled.ArrowBack, "Back")
            }
        },
        title = {
            Text(stringResource(id = R.string.create_todo))
        },
        actions = {
            IconButton(onClick = save) {
                Icon(Icons.Filled.Done, "Save")
            }
        }
    )
}

@Composable
fun CreateToDoBody(
    title: MutableState<String>,
    detail: MutableState<String>,
) {
    Column {
        TextField(
            value = title.value,
            onValueChange = { title.value = it },
            label = { Text(stringResource(id = R.string.todo_title)) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        TextField(
            value = detail.value,
            onValueChange = { detail.value = it },
            label = { Text(stringResource(id = R.string.todo_detail)) },
            modifier = Modifier
                .weight(1.0f, true)
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Preview
@Composable
fun CreatePreview() {
    val navController = rememberNavController()
    val viewModel = CreateToDoViewModel()
    ComposeToDoTheme {
        CreateToDoScreen(navController = navController, viewModel = viewModel)
    }
}