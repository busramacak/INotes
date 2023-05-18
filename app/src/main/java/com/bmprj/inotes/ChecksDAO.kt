package com.bmprj.inotes

import android.annotation.SuppressLint
import android.content.ContentValues

class ChecksDAO {

    fun addChecks(dh:DataBaseHelper, check_text:String, is_checked:Int){
        val db = dh.writableDatabase
        val values = ContentValues()
        values.put("check_text",check_text)
        values.put("is_checked",is_checked)

        db.insertOrThrow("Checks",null, values)
        db.close()
    }

    fun updateChecks(dh:DataBaseHelper,check_id:Int?,is_checked: Int){
        val db = dh.writableDatabase
        val values = ContentValues()

        values.put("is_checked",is_checked)

        db.update("Checks",values,"check_id=?", arrayOf(check_id.toString()))
        db.close()
    }

    fun deleteChecks(dh:DataBaseHelper,check_id:Int?){
        val db = dh.writableDatabase

        db.delete("Checks","check_id=?", arrayOf(check_id.toString()))
        db.close()

    }

    @SuppressLint("Range")
    fun getChecks(dh:DataBaseHelper):ArrayList<Check>{
        val checkList = ArrayList<Check>()
        val db = dh.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM Checks",null)

        while (cursor.moveToNext()){
            val check = Check(cursor.getInt(cursor.getColumnIndex("check_id")).toString(),
                cursor.getString(cursor.getColumnIndex("check_text")),
            cursor.getInt(cursor.getColumnIndex("is_checked")))

            checkList.add(check)
        }

        return checkList
    }
}