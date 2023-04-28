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

    override suspend fun getToDo(id: Int): ToDo {
        return toDoDAO.getToDo(id = id)
    }


    override suspend fun getAllToDo(): Flow<List<ToDo>> {
        return withContext(Dispatchers.IO) {
            toDoDAO.getAllToDo()
        }
    }

    override suspend fun searchTasks(query: String): Flow<List<ToDo>> {
        return withContext(Dispatchers.IO) {
            toDoDAO.searchToDo(query = query)
        }
    }

    override suspend fun filterTasks(query: String): Flow<List<ToDo>> {
        return withContext(Dispatchers.IO) {
            toDoDAO.filterTasks(query = query)
        }
    }


    override fun deleteToDo() {
        coroutineScope.launch(Dispatchers.IO) {
            toDoDAO.deleteToDo()
        }
    }

}