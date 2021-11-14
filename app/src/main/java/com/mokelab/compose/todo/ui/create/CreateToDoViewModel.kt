package com.mokelab.compose.todo.ui.create

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateToDoViewModel @Inject constructor() : ViewModel() {
    fun save(title: String, detail: String) {
        println("title=$title detail=$detail")
    }
}