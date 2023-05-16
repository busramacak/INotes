package com.bmprj.inotes

import android.annotation.SuppressLint
import android.content.ContentValues

class NotesDAO {

    fun addNotes(dh: DataBaseHelper, note_title:String, note:String, note_date:String,note_fav:Int){
        val db = dh.writableDatabase
        val values = ContentValues()
        values.put("note_title",note_title)
        values.put("note",note)
        values.put("note_date",note_date)
        values.put("note_fav",note_fav)

        db.insertOrThrow("Notes",null,values)
        db.close()
    }

    fun updateNotes(dh:DataBaseHelper,prev_note_title:String,note_title: String, note:String, note_date:String){
        val db = dh.writableDatabase
        val values = ContentValues()
        values.put("note_title",note_title)
        values.put("note",note)
        values.put("note_date",note_date)
        db.update("Notes",values,"note_title=?", arrayOf(prev_note_title))
        db.close()
    }

    fun updateFav(dh:DataBaseHelper,note_title:String,note_fav:Int){
        val db=dh.writableDatabase
        val values=ContentValues()
        values.put("note_fav",note_fav)

        db.update("Notes",values,"note_title=?", arrayOf(note_title))
        db.close()
    }

    fun deleteNotes(dh:DataBaseHelper, note_title: String){
        val db=dh.writableDatabase

        db.delete("Notes","note_title=?", arrayOf(note_title))
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
            cursor.getString(cursor.getColumnIndex("note_date")),
            cursor.getInt(cursor.getColumnIndex("note_fav")))

            noteList.add(note)
        }

        return noteList
    }
}