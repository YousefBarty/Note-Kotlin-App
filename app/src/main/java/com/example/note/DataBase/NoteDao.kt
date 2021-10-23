package com.example.todo.DataBase

import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    public fun addNote(noteData: NoteData)

    @Query("Select * From NoteData")
    public fun getAllNotes():List<NoteData>

    @Update
    public fun updateNote(noteData: NoteData)


    @Delete
    public fun deleteNote(noteData: NoteData)
}