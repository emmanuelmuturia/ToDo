package com.example.todo.uilayer

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.datalayer.ToDo
import com.example.todo.datalayer.ToDoModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditToDoViewModel @Inject constructor(app: Application, savedStateHandle: SavedStateHandle): AndroidViewModel(application = app) {

    private var _toDoState = MutableStateFlow<ToDo?>(null)
    val toDoState: StateFlow<ToDo?> = _toDoState.asStateFlow()

    private val database = ToDoModule.provideToDoDatabase(context = app)
    private val dao = ToDoModule.provideToDoDAO(database = database)
    private val toDoRepository = ToDoModule.provideRepository(dao = dao)

    var toDo by mutableStateOf<ToDo?>(null)
        private set

    var toDoTitle by mutableStateOf("")
        private set

    var toDoDescription by mutableStateOf("")
        private set

    private val toDoId = savedStateHandle.get<String>("id")

    init {
        getToDo()
    }

    private fun getToDo() {
        viewModelScope.launch {
            if(toDoId != null) {
                toDo = toDoRepository.getToDo(id = toDoId.toInt())
                toDoTitle = toDo!!.title ?: ""
                toDoDescription = toDo!!.description ?: ""
            }
        }
    }


    fun updateTitle(title: String) {
        toDoTitle = title
    }


    fun updateDescription(description: String) {
        toDoDescription = description
    }

    fun updateToDo() {
        if (toDo != null) {
            viewModelScope.launch {
                val update = toDo!!.copy(
                    title = toDoTitle,
                    description = toDoDescription
                )
                toDoRepository.updateToDo(toDo = update)
            }
        }
    }

}