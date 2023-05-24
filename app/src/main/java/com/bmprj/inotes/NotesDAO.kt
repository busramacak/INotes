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

    fun updateNotes(dh:DataBaseHelper,note_id:Int?,note_title: String, note:String, note_date:String){
        val db = dh.writableDatabase
        val values = ContentValues()
        values.put("note_title",note_title)
        values.put("note",note)
        values.put("note_date",note_date)
        db.update("Notes",values,"note_id=?", arrayOf(note_id.toString()))
        db.close()
    }

    fun updateFav(dh:DataBaseHelper,note_id:Int?,note_fav:Int){
        val db=dh.writableDatabase
        val values=ContentValues()
        values.put("note_fav",note_fav)

        db.update("Notes",values,"note_id=?", arrayOf(note_id.toString()))
        db.close()
    }

    fun deleteNotes(dh:DataBaseHelper, note_id: Int?){
        val db=dh.writableDatabase

        db.delete("Notes","note_id=?", arrayOf(note_id.toString()))
        db.close()
    }

    fun deleteNotes(dh:DataBaseHelper){
        val db=dh.writableDatabase

        db.delete("Notes",null,null)
        db.close()
    }
    @SuppressLint("Range")
    fun getNotes(dh: DataBaseHelper):ArrayList<Note>{
        val noteList=ArrayList<Note>()
        val db = dh.writableDatabase

        val cursor = db.rawQuery("SELECT * FROM Notes", null)

        while(cursor.moveToNext()){
            val note = Note(cursor.getInt(cursor.getColumnIndex("note_id")).toString(),
                cursor.getString(cursor.getColumnIndex("note_title")),
            cursor.getString(cursor.getColumnIndex("note")),
            cursor.getString(cursor.getColumnIndex("note_date")),
            cursor.getInt(cursor.getColumnIndex("note_fav")))

            noteList.add(note)
        }

        return noteList
    }
}