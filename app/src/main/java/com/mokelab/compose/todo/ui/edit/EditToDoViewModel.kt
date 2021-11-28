package com.mokelab.compose.todo.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mokelab.compose.todo.model.todo.ToDo
import com.mokelab.compose.todo.repository.todo.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditToDoViewModel @Inject constructor(
    private val repo: ToDoRepository
) : ViewModel() {
    private val todoId = MutableStateFlow(-1)

    @ExperimentalCoroutinesApi
    val todo: Flow<ToDo> = todoId.flatMapLatest { todoId -> repo.getById(todoId) }

    val errorMessage = MutableStateFlow("")
    val done = MutableStateFlow(false)

    fun setId(todoId: Int) {
        this.todoId.value = todoId
    }

    fun save(todo: ToDo, title: String, detail: String) {
        if (title.trim().isEmpty()) {
            errorMessage.value = "Input title"
            return
        }
        viewModelScope.launch {
            try {
                repo.update(todo, title, detail)
                done.value = true
            } catch (e: Exception) {
                errorMessage.value = e.message.toString()
            }
        }
    }
}