package com.mokelab.compose.todo.ui.main

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mokelab.compose.todo.R
import com.mokelab.compose.todo.ui.theme.ComposeToDoTheme

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel,
) {
    Scaffold(
        topBar = { MainTopBar() },
        floatingActionButton = { MainFAB(navController) }
    ) {
        // ToDoのリスト表示をここに書く
    }
}

@Composable
fun MainTopBar() {
    TopAppBar(
        title = {
            Text(stringResource(id = R.string.app_name))
        },
    )
}

@Composable
fun MainFAB(navController: NavController) {
    FloatingActionButton(onClick = {
        // 作成画面へ画面遷移
        navController.navigate("create")
    }) {
        Icon(Icons.Filled.Add, "Add")
    }
}


@Preview
@Composable
fun MainScreenPreview() {
    val navController = rememberNavController()
    val viewModel = MainViewModel()
    ComposeToDoTheme {
        MainScreen(navController = navController, viewModel = viewModel)
    }
}