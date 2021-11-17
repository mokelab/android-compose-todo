package com.mokelab.compose.todo.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mokelab.compose.todo.model.todo.ToDo
import com.mokelab.compose.todo.repository.todo.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditToDoViewModel @Inject constructor(
    private val repo: ToDoRepository
) : ViewModel() {
    private lateinit var _todo: Flow<ToDo>
    val todo: Flow<ToDo> get() = _todo

    val errorMessage = MutableStateFlow("")
    val done = MutableStateFlow(false)

    fun setId(todoId: Int) {
        _todo = repo.getById(todoId)
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