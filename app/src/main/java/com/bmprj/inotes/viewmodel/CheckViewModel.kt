package com.bmprj.inotes.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bmprj.inotes.data.DataBaseHelper
import com.bmprj.inotes.data.dao.ChecksDAO
import com.bmprj.inotes.model.Check

class CheckViewModel :ViewModel() {

    val check = MutableLiveData<ArrayList<Check>>()

    fun refresh(context: Context){
        getData(context)
    }

    fun addCheck(context: Context,editText:String){
        val dh = DataBaseHelper(context)
        ChecksDAO().addChecks(dh,editText,0)
    }

    private fun getData(context: Context){
        val list = ArrayList<Check>()

        val dh = DataBaseHelper(context)
        val checkList = ChecksDAO().getChecks(dh)


        for(i in checkList){
            list.add(i)
        }

        check.value=list

    }
}