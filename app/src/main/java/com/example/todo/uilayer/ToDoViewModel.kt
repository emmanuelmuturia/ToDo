package com.example.todo.uilayer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.datalayer.ToDo
import com.example.todo.datalayer.ToDoDatabase
import com.example.todo.datalayer.ToDoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ToDoViewModel(app: Application) : AndroidViewModel(application = app) {

    private val database = ToDoDatabase.getDatabase(app)
    private val dao = database.todoDAO()
    private val toDoRepository = ToDoRepository(toDoDAO = dao)

    private var _toDoState = MutableStateFlow<List<ToDo>>(emptyList())
    val toDoState: StateFlow<List<ToDo>> = _toDoState.asStateFlow()

    init {
        viewModelScope.launch {
            toDoRepository.getToDo().collectLatest {
                _toDoState.value = it
            }
        }
    }

    fun addToDo(toDo: ToDo) {
        toDoRepository.addToDo(toDo = toDo)
    }

    fun deleteToDo() {
        toDoRepository.deleteToDo()
    }

}