package com.example.todo.datalayer

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ToDoRepositoryImpl(private val toDoDAO: ToDoDAO) : ToDoRepository {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun addToDo(toDo: ToDo) {
        coroutineScope.launch(Dispatchers.IO) {
            toDoDAO.insertToDo(toDo = toDo)
        }
    }

    override fun updateToDo(toDo: ToDo) {
        coroutineScope.launch(Dispatchers.IO) {
            toDoDAO.updateToDo(toDo = toDo)
        }
    }


    override suspend fun getToDo(): Flow<List<ToDo>> {
        return withContext(Dispatchers.IO) {
            toDoDAO.getToDo()
        }
    }

    override fun deleteToDo() {
        coroutineScope.launch(Dispatchers.IO) {
            toDoDAO.deleteToDo()
        }
    }

}