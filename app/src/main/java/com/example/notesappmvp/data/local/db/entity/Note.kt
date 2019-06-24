package com.example.notesappmvp.data.local.db.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
data class Note(

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "entryid")
    public var mId:String,

    @ColumnInfo(name = "title")
    public var mTitle: String,

    @ColumnInfo(name = "description")
    public var mDescription: String,

    @ColumnInfo(name = "completed")
    public var mCompleted: Boolean

) {

    //new note constructor
    constructor(title: String,description:String):this(UUID.randomUUID().toString(),title,description,false)

    //new note editing
    constructor(title: String,description:String,entryid:String):this(entryid,title,description,false)

    //new note completed
    constructor(title: String,description:String,isComplted:Boolean):this(UUID.randomUUID().toString(),title,description,isComplted)

}