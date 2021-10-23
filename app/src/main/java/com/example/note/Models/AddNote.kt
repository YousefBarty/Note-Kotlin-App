package com.example.note.Models

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.note.R
import com.example.todo.DataBase.NoteData
import com.example.todo.DataBase.NoteDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddNote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)


        var noteTitle = findViewById<EditText>(R.id.noteTilte)
        var NoteDescrption = findViewById<EditText>(R.id.noteDescription)
        var saveNote = findViewById<Button>(R.id.saveNote)


        saveNote.setOnClickListener {


            var title = noteTitle.text.toString()
            var descrption = NoteDescrption.text.toString()

            val date = Calendar.getInstance().time
            val formatter = SimpleDateFormat.getDateTimeInstance() //or use getDateInstance()
            val formatedDate = formatter.format(date)


            if (TextUtils.isEmpty(title)) {
                noteTitle.setError("please enter title")
            } else if (TextUtils.isEmpty(descrption)) {
                NoteDescrption.setError("please enter descrption")
            } else {

                noteTitle.setError(null)
                NoteDescrption.setError(null)

                var newtask = NoteData(title = title, description = descrption,date = formatedDate)

                var dao = NoteDataBase(this).noteDao()

                CoroutineScope(Dispatchers.Main).launch {
                    try {

                        dao.addNote(newtask)

                        Toast.makeText(
                            this@AddNote,
                            "Note added successfully",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        finish()
                    } catch (e: Exception) {
                        // handler error
                        Toast.makeText(this@AddNote, e.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

    }
}