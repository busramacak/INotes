@file:Suppress("DEPRECATED_IDENTITY_EQUALS")

package com.bmprj.inotes.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bmprj.inotes.data.DataBaseHelper
import com.bmprj.inotes.data.dao.NotesDAO
import com.bmprj.inotes.model.Note
import java.util.Locale
import kotlin.collections.ArrayList

class SearchViewModel : ViewModel() {

    val search = MutableLiveData<ArrayList<Note>>()

    fun refresh(context:Context,query:String){
        getData(context,query)
    }

    private fun getData(context: Context, query: String){


        val dh = DataBaseHelper(context)
        val searchNoteList = NotesDAO().getNotes(dh)
        val list = ArrayList<Note>()


        for(i in searchNoteList){

            query.lowercase(Locale.getDefault())
            if (query.length !== 0) {
                if (i.note_title.lowercase(Locale.getDefault()).contains(query)) {
                    list.add(i)
                }
            }

        }

        search.value=list

    }
}