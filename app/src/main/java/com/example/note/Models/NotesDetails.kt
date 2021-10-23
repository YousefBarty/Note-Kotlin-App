package com.example.note.Models

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.note.Controller.MainActivity
import com.example.note.R
import com.example.swoosh.Utilities.EXTRA_DETAILS
import com.example.todo.DataBase.NoteData
import com.example.todo.DataBase.NoteDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class NotesDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_details)


        var noteTilteDetails = findViewById<TextView>(R.id.tasktiltedetails)
        var NoteDescriptionDetails = findViewById<TextView>(R.id.taskdescriptiondetails)

        var setTime = findViewById<ImageView>(R.id.time)


        val taskDE = intent.getParcelableExtra<NoteDetailsData>(EXTRA_DETAILS)
        noteTilteDetails.text = taskDE?.title.toString()
        NoteDescriptionDetails.text = taskDE?.descption.toString()

        var backBtn = findViewById<ImageView>(R.id.backBtn)
        var saveBtn = findViewById<ImageView>(R.id.saveBtn)
        var delBtn = findViewById<ImageView>(R.id.deleteBtn)
        backBtn.setOnClickListener {

            var backIntent = Intent(this, MainActivity::class.java)
            startActivity(backIntent)

        }

        saveBtn.setOnClickListener {

            val date = Calendar.getInstance().time
            val formatter = SimpleDateFormat.getDateTimeInstance()
            val formatedDate = formatter.format(date)

            var updataNote = NoteData(
                id = taskDE!!.id,
                title = noteTilteDetails.text.toString(),
                description = NoteDescriptionDetails.text.toString(),
                date = formatedDate
            )

            var dao = NoteDataBase(this).noteDao()

            CoroutineScope(Dispatchers.Main).launch {
                try {

                    dao.updateNote(updataNote)

                    Toast.makeText(
                        this@NotesDetails,
                        "Note Updated successfully",
                        Toast.LENGTH_LONG
                    )
                        .show()
                    finish()
                } catch (e: Exception) {
                    Toast.makeText(this@NotesDetails, e.toString(), Toast.LENGTH_LONG).show()
                }
            }

        }

        delBtn.setOnClickListener {

            var buildAlert = AlertDialog.Builder(this)
            buildAlert.setTitle("Delete Note Warning")
            buildAlert.setIcon(R.drawable.ic_delete_22)
            buildAlert.setMessage("Are You Sure That U What To Delete This Note ?")

            buildAlert.setCancelable(false)

            buildAlert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->

                val date = Calendar.getInstance().time
                val formatter = SimpleDateFormat.getDateTimeInstance()
                val formatedDate = formatter.format(date)

                var deleteNote = NoteData(
                    id = taskDE!!.id,
                    title = noteTilteDetails.text.toString(),
                    description = NoteDescriptionDetails.text.toString(),
                    date = formatedDate
                )


                var dao = NoteDataBase(this).noteDao()

                CoroutineScope(Dispatchers.Main).launch {
                    try {

                        dao.deleteNote(deleteNote)

                        Toast.makeText(
                            this@NotesDetails,
                            "Note Deleted successfully",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        finish()
                    } catch (e: Exception) {
                        Toast.makeText(this@NotesDetails, e.toString(), Toast.LENGTH_LONG).show()
                    }
                }

                dialog.dismiss()
            })

            buildAlert.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->

                dialog.dismiss()
            })


            buildAlert.create().show()

        }
    }
}