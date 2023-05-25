package com.bmprj.inotes

import android.annotation.SuppressLint
import android.content.ContentValues

class ThemeDAO {

    fun addTheme(dh:DataBaseHelper, theme_is_dark:Int,theme_id: Int){
        val db = dh.writableDatabase
        val values = ContentValues()
        values.put("theme_is_dark",theme_is_dark)
        values.put("theme_id",theme_id)

        db.insertOrThrow("Theme",null,values)
        db.close()
    }

    fun updateTheme(dh:DataBaseHelper,theme_is_dark: Int, theme_id:Int){
        val db=dh.writableDatabase
        val values=ContentValues()
        values.put("theme_is_dark",theme_is_dark)
        db.update("Theme",values,"theme_id=?", arrayOf(theme_id.toString()))
        db.close()
    }

    @SuppressLint("Range")
    fun getTheme(dh:DataBaseHelper):ArrayList<Theme>{
        val themeList=ArrayList<Theme>()
        val db=dh.writableDatabase
        val cursor=db.rawQuery("SELECT * FROM Theme",null)

        while(cursor.moveToNext()){
            val theme=Theme(cursor.getInt(cursor.getColumnIndex("theme_is_dark")),
            cursor.getInt(cursor.getColumnIndex("theme_id")))

            themeList.add(theme)
        }

        return themeList

    }
}