package com.bmprj.inotes.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bmprj.inotes.data.DataBaseHelper
import com.bmprj.inotes.data.dao.NotesDAO
import com.bmprj.inotes.model.Note
import java.text.SimpleDateFormat
import java.util.Calendar

class AddNoteViewModel:ViewModel() {

    val note = MutableLiveData<ArrayList<Note>>()
    val date = Calendar.getInstance().time
    @SuppressLint("SimpleDateFormat")
    val formatter = SimpleDateFormat("dd MMM yy")
    val current = formatter.format(date).toString()


    fun update(context: Context,note_id:Int?,title:String,note:String){

        val dh = DataBaseHelper(context)
        NotesDAO().updateNotes(dh, note_id, title, note, current)

    }


    fun add(context: Context,title: String,note: String){
        val dh = DataBaseHelper(context)
        NotesDAO().addNotes(dh,title, note,current,0)
    }
}