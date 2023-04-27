package com.example.todo.datalayer

import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    fun addToDo(toDo: ToDo)

    fun updateToDo(toDo: ToDo)

    suspend fun getToDo(id: Int) : ToDo

    suspend fun getAllToDo(): Flow<List<ToDo>>

    suspend fun searchToDo(query: String): Flow<List<ToDo>>

    fun deleteToDo()

}