package com.example.todo.datalayer

import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    fun addToDo(toDo: ToDo)

    fun updateToDo(toDo: ToDo)

    suspend fun getToDo(id: Int) : ToDo

    suspend fun getAllToDo(): Flow<List<ToDo>>

    suspend fun searchTasks(query: String): Flow<List<ToDo>>

    suspend fun filterTasks(query: String): Flow<List<ToDo>>

    fun deleteToDo()

}