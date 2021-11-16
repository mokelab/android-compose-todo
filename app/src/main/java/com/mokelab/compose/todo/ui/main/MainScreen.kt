package com.mokelab.compose.todo.ui.main

import android.text.format.DateFormat
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mokelab.compose.todo.R
import com.mokelab.compose.todo.model.todo.ToDo
import com.mokelab.compose.todo.repository.todo.ToDoRepository
import com.mokelab.compose.todo.ui.theme.ComposeToDoTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel,
) {
    val todoList = viewModel.todoList.collectAsState(emptyList())

    Scaffold(
        topBar = { MainTopBar() },
        floatingActionButton = { MainFAB(navController) }
    ) {
        ToDoList(todoList) { todo ->
            navController.navigate("detail/${todo._id}")
        }
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

@Composable
fun ToDoList(list: State<List<ToDo>>, itemSelected: (todo: ToDo) -> Unit) {
    LazyColumn {
        items(list.value) { todo ->
            ToDoListItem(todo, itemSelected)
        }
    }
}

@Composable
fun ToDoListItem(todo: ToDo, itemSelected: (todo: ToDo) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .clickable { itemSelected(todo) }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            todo.title,
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            DateFormat.format("yyyy-MM-dd hh:mm:ss", todo.created).toString(),
            style = MaterialTheme.typography.body2
        )
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    val navController = rememberNavController()
    val viewModel = MainViewModel(object : ToDoRepository {
        override suspend fun create(title: String, detail: String): ToDo {
            TODO("Not yet implemented")
        }

        override fun getAll(): Flow<List<ToDo>> {
            return flow {
                emit(listOf())
            }
        }

        override fun getById(todoId: Int): Flow<ToDo> {
            TODO("Not yet implemented")
        }
    })
    ComposeToDoTheme {
        MainScreen(navController = navController, viewModel = viewModel)
    }
}