package com.example.todo.datalayer

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(toDo: ToDo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateToDo(toDo: ToDo)

    @Query("SELECT * FROM myToDo where toDoId = :id")
    suspend fun getToDo(id: Int) : ToDo

    @Query("SELECT * FROM myToDo ORDER BY toDoId ASC")
    fun getAllToDo(): Flow<List<ToDo>>

    @Query("DELETE FROM myToDo")
    suspend fun deleteToDo()

}