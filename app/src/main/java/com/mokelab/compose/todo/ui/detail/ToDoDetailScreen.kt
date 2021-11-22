package com.mokelab.compose.todo.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mokelab.compose.todo.R
import com.mokelab.compose.todo.model.todo.ToDo
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Composable
fun ToDoDetailScreen(
    navController: NavController,
    viewModel: ToDoDetailViewModel,
) {
    val scaffoldState = rememberScaffoldState()
    val todo = viewModel.todo.collectAsState(emptyToDo)
    val showDialog = remember { mutableStateOf(false) }

    val errorMessage = viewModel.errorMessage.collectAsState()
    val deleted = viewModel.deleted.collectAsState()

    if (errorMessage.value.isNotEmpty()) {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = errorMessage.value
            )
            viewModel.errorMessage.value = ""
        }
    }

    if (deleted.value) {
        // 再コンポーズ時にもう一度実行されたら困る
        viewModel.deleted.value = false
        navController.popBackStack()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            DetailTopBar(navController, todo.value, {
                navController.navigate("edit/${todo.value._id}")
            }, {
                showDialog.value = true
            })
        },
    ) {
        DetailBody(todo.value, showDialog) {
            viewModel.delete(todo.value)
        }
    }
}

@Composable
fun DetailTopBar(
    navController: NavController,
    todo: ToDo,
    toEdit: () -> Unit,
    deleteClicked: () -> Unit,
) {
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(Icons.Filled.ArrowBack, "Back")
            }
        },
        title = {
            if (todo._id == emptyToDoId) {
                Text(stringResource(id = R.string.loading))
            } else {
                Text(todo.title)
            }
        },
        actions = {
            if (todo._id != emptyToDoId) {
                IconButton(onClick = toEdit) {
                    Icon(Icons.Filled.Edit, "Edit")
                }
                IconButton(onClick = { showMenu = !showMenu }) {
                    Icon(Icons.Filled.MoreVert, "Menu")
                }
                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { showMenu = false }
                ) {
                    DropdownMenuItem(onClick = {
                        showMenu = false
                        deleteClicked()
                    }) {
                        Text(stringResource(id = R.string.delete_todo))
                    }
                }
            }
        }
    )
}

@Composable
fun DetailBody(
    todo: ToDo,
    showDialog: MutableState<Boolean>,
    performDelete: () -> Unit
) {
    Column {
        Text(
            todo.title,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
        )
        Text(
            todo.detail,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .weight(1.0f, true)
                .padding(horizontal = 8.dp, vertical = 8.dp)
        )
    }
    if (showDialog.value) {
        AlertDialog(onDismissRequest = {
            showDialog.value = false
        }, title = {
            Text(stringResource(id = R.string.delete_message))
        }, confirmButton = {
            TextButton(onClick = {
                showDialog.value = false
                performDelete()
            }) {
                Text(stringResource(id = android.R.string.ok))
            }
        }, dismissButton = {
            TextButton(onClick = { showDialog.value = false }) {
                Text(stringResource(id = android.R.string.cancel))
            }
        }
        )
    }
}

private const val emptyToDoId = -1
private val emptyToDo = ToDo(
    _id = emptyToDoId,
    title = "",
    detail = "",
    created = 0,
    modified = 0
)
