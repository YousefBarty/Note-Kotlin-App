package com.example.todo.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.note.R
import com.example.todo.DataBase.NoteData

class NoteAdapter(val data: List<NoteData>,private val listener:OnItemClickListerner) :
    RecyclerView.Adapter<NoteAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)

        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        var NoteModel = data.get(position)

        holder.noteTitle.text = NoteModel.title
        holder.noteDate.text = NoteModel.date

    }

    override fun getItemCount(): Int {

        return data.size
    }


   inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {

        var noteTitle: TextView = itemView.findViewById(R.id.noteTilteItem)
        var noteDate: TextView = itemView.findViewById(R.id.noteDateItem)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            listener.OnItemClicked(data[adapterPosition])
        }


   }

    interface OnItemClickListerner
    {
        fun OnItemClicked(adapterPosition: NoteData)
    }

}