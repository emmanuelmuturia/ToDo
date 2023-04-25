package com.example.todo.datalayer

import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    fun addToDo(toDo: ToDo)

    suspend fun getToDo(): Flow<List<ToDo>>

    fun deleteToDo()

}