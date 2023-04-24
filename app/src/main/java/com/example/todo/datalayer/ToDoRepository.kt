package com.example.todo.datalayer

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ToDoRepository(private val toDoDAO: ToDoDAO) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addToDo(toDo: ToDo) {
        coroutineScope.launch(Dispatchers.IO) {
            toDoDAO.insertToDo(toDo = toDo)
            getToDo()
        }
    }


    suspend fun getToDo(): Flow<List<ToDo>> {
        return withContext(Dispatchers.IO) {
            toDoDAO.getToDo()
        }
    }

    fun deleteToDo() {
        coroutineScope.launch(Dispatchers.IO) {
            toDoDAO.deleteToDo()
        }
    }

}