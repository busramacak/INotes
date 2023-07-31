package com.bmprj.inotes.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bmprj.inotes.data.DataBaseHelper
import com.bmprj.inotes.data.dao.NotesDAO
import com.bmprj.inotes.model.Note

class FavNoteViewModel : ViewModel(){

    val note = MutableLiveData<ArrayList<Note>>()


    fun refresh(context: Context){

        getData(context)
    }


    private fun getData(context: Context){

        val list = ArrayList<Note>()
        val dh = DataBaseHelper(context)
        val favNoteList = NotesDAO().getNotes(dh)

        for(i in favNoteList){
            if(i.note_fav==1){
                list.add(i)
            }
        }
        note.value=list

    }
}