package com.example.todo.uilayer

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.datalayer.ToDo
import com.example.todo.datalayer.ToDoModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(app: Application) : AndroidViewModel(application = app) {

    private val database = ToDoModule.provideToDoDatabase(context = app)
    private val dao = ToDoModule.provideToDoDAO(database = database)
    private val toDoRepository = ToDoModule.provideRepository(dao = dao)


    private var _toDoState = MutableStateFlow<List<ToDo>>(emptyList())
    val toDoState: StateFlow<List<ToDo>> = _toDoState.asStateFlow()

    var myToDoSearch by mutableStateOf("")

    init {
        viewModelScope.launch {
            toDoRepository.getAllToDo().collectLatest {
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