package com.bmprj.inotes.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context):SQLiteOpenHelper(context,"note",null,8) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("CREATE TABLE Notes (note_id INTEGER PRIMARY KEY AUTOINCREMENT, note_title TEXT, note TEXT,note_date TEXT, note_fav INTEGER);")
        p0?.execSQL("CREATE TABLE Checks (check_id INTEGER PRIMARY KEY AUTOINCREMENT, check_text TEXT, is_checked INTEGER);")
        p0?.execSQL("CREATE TABLE Theme (theme_is_dark INTEGER, theme_id INTEGER)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS Notes")
        p0?.execSQL("DROP TABLE IF EXISTS Checks")
        p0?.execSQL("DROP TABLE IF EXISTS Theme")
        onCreate(p0)
    }

}