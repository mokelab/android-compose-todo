package com.mokelab.compose.todo.ui.main

import androidx.lifecycle.ViewModel
import com.mokelab.compose.todo.repository.todo.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: ToDoRepository
) : ViewModel() {
    val todoList = repo.getAll()
}