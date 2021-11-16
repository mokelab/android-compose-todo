package com.mokelab.compose.todo.ui.detail

import androidx.lifecycle.ViewModel
import com.mokelab.compose.todo.model.todo.ToDo
import com.mokelab.compose.todo.repository.todo.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ToDoDetailViewModel @Inject constructor(
    private val repo: ToDoRepository
) : ViewModel() {
    private lateinit var _todo: Flow<ToDo>
    val todo: Flow<ToDo> get() = _todo

    fun setId(todoId: Int) {
        _todo = repo.getById(todoId)
    }

}