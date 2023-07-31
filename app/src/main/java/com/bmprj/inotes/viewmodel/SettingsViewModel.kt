package com.bmprj.inotes.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bmprj.inotes.R
import com.bmprj.inotes.data.DataBaseHelper
import com.bmprj.inotes.data.dao.NotesDAO
import com.bmprj.inotes.data.dao.ThemeDAO
import com.bmprj.inotes.model.Theme

class SettingsViewModel: ViewModel() {

    val setting = MutableLiveData<Theme>()

    fun getTheme(dh:DataBaseHelper){

        val themeList = ThemeDAO().getTheme(dh)

        setting.value=themeList[0]
    }

    fun setTheme(dh:DataBaseHelper,is_dark:Int,id:Int){
        val t = Theme(is_dark,id)

        ThemeDAO().updateTheme(dh,is_dark,id)
        setting.value=t
    }

    fun deleteAllNotes(dh: DataBaseHelper,context: Context){
        NotesDAO().deleteNotes(dh)
        Toast.makeText(context, R.string.deleteSuccessfull, Toast.LENGTH_SHORT).show()

    }
}