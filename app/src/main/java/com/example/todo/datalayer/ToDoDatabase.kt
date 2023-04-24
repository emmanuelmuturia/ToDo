package com.example.todo.datalayer

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToDo::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun todoDAO(): ToDoDAO

    companion object {

        @Volatile
        private var instance: ToDoDatabase? = null

        fun getDatabase(context: Context) : ToDoDatabase {
            return  instance ?: synchronized(this) {
                Room.databaseBuilder(context = context, klass = ToDoDatabase::class.java, name = "myToDoDatabase")
                    .build()
                    .also { instance = it }
            }
        }

    }

}