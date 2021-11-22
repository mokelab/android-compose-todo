package com.mokelab.compose.todo.ui.detail

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
class ToDoDetailViewModel @Inject constructor(
    private val repo: ToDoRepository
) : ViewModel() {
    private val todoId = MutableStateFlow(-1)
    val errorMessage = MutableStateFlow("")
    val deleted = MutableStateFlow(false)

    @ExperimentalCoroutinesApi
    val todo: Flow<ToDo> = todoId.flatMapLatest { todoId -> repo.getById(todoId) }

    fun setId(todoId: Int) {
        this.todoId.value = todoId
    }

    fun delete(todo: ToDo) {
        viewModelScope.launch {
            try {
                repo.delete(todo)
                deleted.value = true
            } catch (e: Exception) {
                errorMessage.value = e.message ?: ""
            }
        }
    }
}