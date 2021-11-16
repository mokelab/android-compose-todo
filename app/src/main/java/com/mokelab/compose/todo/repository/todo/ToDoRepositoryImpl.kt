package com.mokelab.compose.todo.repository.todo

import com.mokelab.compose.todo.model.todo.ToDo
import com.mokelab.compose.todo.model.todo.ToDoDAO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(
    private val dao: ToDoDAO
) : ToDoRepository {
    override suspend fun create(title: String, detail: String): ToDo {
        val todo = ToDo(
            title = title,
            detail = detail,
            created = System.currentTimeMillis(),
            modified = System.currentTimeMillis(),
        )
        dao.create(todo)
        return todo
    }

    override fun getAll(): Flow<List<ToDo>> {
        return dao.getAll()
    }
}