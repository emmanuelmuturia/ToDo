package com.example.todo.datalayer

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ToDoModule {

    @Provides
    @Singleton
    fun provideToDoDatabase(@ApplicationContext context: Context): ToDoDatabase {
        return ToDoDatabase.getDatabase(context)
    }

    @Provides
    fun provideToDoDAO(database: ToDoDatabase): ToDoDAO {
        return database.todoDAO()
    }

    @Provides
    fun provideRepository(dao: ToDoDAO): ToDoRepositoryImpl {
        return ToDoRepositoryImpl(toDoDAO = dao)
    }

}