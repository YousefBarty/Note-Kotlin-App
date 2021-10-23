package com.example.todo.DataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteData(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val date:String,
    var title: String,
    var description: String
)
