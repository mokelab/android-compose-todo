package com.mokelab.compose.todo.repository.todo

import com.mokelab.compose.todo.model.todo.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    suspend fun create(title: String, detail: String): ToDo
    fun getAll(): Flow<List<ToDo>>
    fun getById(todoId: Int): Flow<ToDo>
    suspend fun update(todo: ToDo, title: String, detail: String)
}