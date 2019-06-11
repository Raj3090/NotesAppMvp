package com.example.notesappmvp.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappmvp.R
import com.example.notesappmvp.data.local.db.entity.Note

class NotesAdapter(private val notes:List<Note>) : RecyclerView.Adapter<NotesAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.notes_item,parent,false))
    }

    override fun getItemCount()=notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {



    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


}