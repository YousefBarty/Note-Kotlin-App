package com.example.note.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.note.Models.AddNote
import com.example.note.Models.NoteDetailsData
import com.example.note.Models.NotesDetails
import com.example.note.R
import com.example.swoosh.Utilities.EXTRA_DETAILS
import com.example.todo.Adapter.NoteAdapter
import com.example.todo.DataBase.NoteData
import com.example.todo.DataBase.NoteDataBase

class MainActivity : AppCompatActivity(), NoteAdapter.OnItemClickListerner{

    lateinit var noteRecyceler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var addNewNote: View = findViewById<Button>(R.id.addNoteBtn)
        noteRecyceler = findViewById(R.id.notesRecycler)
        var changeShowDesignBtn = findViewById<ImageView>(R.id.changeShowDesignBtn)
        var flag = true


        addNewNote.setOnClickListener {

            var addNoteIntent = Intent(this, AddNote::class.java)
            startActivity(addNoteIntent)

        }

        changeShowDesignBtn.setOnClickListener {

            if (flag) {
                flag=false
                noteRecyceler.apply {
                    layoutManager = GridLayoutManager(this@MainActivity, 2)
                    adapter?.notifyDataSetChanged()
                }
            } else {
                flag=true
                noteRecyceler.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter?.notifyDataSetChanged()
                }

            }
        }
    }

    override fun onResume() {
        super.onResume()

        var data = NoteDataBase(context = this).noteDao().getAllNotes()
        noteRecyceler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            //layoutManager = GridLayoutManager(this@MainActivity,2)
            adapter = NoteAdapter(data,this@MainActivity)
            noteRecyceler.adapter = adapter
        }
    }

    override fun OnItemClicked(noteData: NoteData) {
        var noteDetails = NoteDetailsData(0,"", "")
        noteDetails.title = noteData.title
        noteDetails.descption = noteData.description
        noteDetails.id=noteData.id
        var todoDetailsIntent = Intent(this, NotesDetails::class.java)
        todoDetailsIntent.putExtra(EXTRA_DETAILS, noteDetails)
        startActivity(todoDetailsIntent)    }
}