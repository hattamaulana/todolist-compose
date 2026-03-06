package com.github.hattamaulana.genesys.todo.di

import android.app.Application
import androidx.room.Room
import com.github.hattamaulana.genesys.todo.data.sources.TodoDao
import com.github.hattamaulana.genesys.todo.data.sources.TodoDatabase
import com.github.hattamaulana.genesys.todo.repository.TodoRepository
import com.github.hattamaulana.genesys.todo.repository.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): TodoDatabase {
        return Room.databaseBuilder(
            app,
            TodoDatabase::class.java,
            "todo_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(database: TodoDatabase): TodoDao {
        return database.todoDao()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(dao: TodoDao): TodoRepository {
        return TodoRepositoryImpl(dao)
    }
}