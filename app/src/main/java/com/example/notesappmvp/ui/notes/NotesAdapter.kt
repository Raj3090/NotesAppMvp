package com.example.notesappmvp.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.notesappmvp.R
import com.example.notesappmvp.data.local.db.entity.Note

class NotesAdapter(
    private val notes: List<Note>,
    val mItemListener: NoteItemListener
) : RecyclerView.Adapter<NotesAdapter.ViewHolder>(){


    interface NoteItemListener {

        fun onNoteClick(clickedNote: Note)

        fun onCompleteNoteClick(completedNote: Note)

        fun onActivateNoteClick(activatedNote: Note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.notes_item,parent,false))
    }

    override fun getItemCount()=notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note:Note=notes.get(position)

        holder.noteTitleTv.setText(note.mTitle)
        holder.noteDescriptionTv.setText(note.mDescription)

        holder.row.setOnClickListener {
            mItemListener.onNoteClick(note)
        }

        holder.completeCheckBox.setOnClickListener {

            if(!note.mCompleted){
                mItemListener.onCompleteNoteClick(note)
            }else{
                mItemListener.onActivateNoteClick(note)
            }

        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val noteTitleTv=itemView.findViewById<TextView>(R.id.titleTv)

        val noteDescriptionTv=itemView.findViewById<TextView>(R.id.descriptionTv)

        val completeCheckBox=itemView.findViewById<CheckBox>(R.id.checkbox)

        val row=itemView.findViewById<ConstraintLayout>(R.id.row)
    }


}