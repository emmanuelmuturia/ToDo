package com.example.todo.datalayer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "myToDo")
data class ToDo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "toDoId")
    val id: Int = 0,
    @ColumnInfo(name = "toDoTitle")
    val title: String,
    @ColumnInfo(name = "toDoDescription")
    val description: String?
)
