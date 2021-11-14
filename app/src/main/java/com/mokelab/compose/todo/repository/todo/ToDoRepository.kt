package com.mokelab.compose.todo.repository.todo

import com.mokelab.compose.todo.model.todo.ToDo

interface ToDoRepository {
    suspend fun create(title: String, detail: String): ToDo
}