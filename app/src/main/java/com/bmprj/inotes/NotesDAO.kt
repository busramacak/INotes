package com.bmprj.inotes

import android.annotation.SuppressLint
import android.content.ContentValues

class NotesDAO {

    fun addNotes(dh: DataBaseHelper, note_title:String, note:String, note_date:String){
        val db = dh.writableDatabase
        val values = ContentValues()
        values.put("note_title",note_title)
        values.put("note",note)
        values.put("note_date",note_date)

        db.insertOrThrow("Notes",null,values)
        db.close()
    }

    @SuppressLint("Range")
    fun getNotes(dh: DataBaseHelper):ArrayList<Note>{
        val noteList=ArrayList<Note>()
        val db = dh.writableDatabase

        val cursor = db.rawQuery("SELECT * FROM Notes", null)

        while(cursor.moveToNext()){
            val note = Note(cursor.getString(cursor.getColumnIndex("note_title")),
            cursor.getString(cursor.getColumnIndex("note")),
            cursor.getString(cursor.getColumnIndex("note_date")))

            noteList.add(note)
        }

        return noteList
    }
}