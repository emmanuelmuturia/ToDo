package com.example.todo.datalayer

import android.app.Application

class ToDoApplication: Application() {
    val database: ToDoDatabase by lazy { ToDoDatabase.getDatabase(this) }
}