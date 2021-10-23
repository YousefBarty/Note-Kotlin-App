package com.example.todo.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [NoteData::class], version = 2)
abstract class NoteDataBase : RoomDatabase(){

    abstract fun noteDao(): NoteDao

    companion object {

        val appDatabaseName = "AppDataBase"


        @Volatile
        private var instance: NoteDataBase? = null


        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context, NoteDataBase::class.java, appDatabaseName)
            .allowMainThreadQueries()
            .build()
    }


}