package com.bmprj.inotes

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.bmprj.inotes.databinding.ActivitySettingsBinding
import java.util.*


class SettingsActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySettingsBinding
    private val dh=DataBaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_settings)
        binding.settingsDesign=this

        val themeList = ThemeDAO().getTheme(dh)

        if(themeList.get(0).theme_id==0 && themeList.get(0).theme_is_dark==1){
            binding.switchDark.isChecked=true
            binding.switchLight.isChecked=false
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else{
            binding.switchDark.isChecked=false
            binding.switchLight.isChecked=true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }





    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
    }

    fun backClick(view: View){
        startActivity(Intent(this,MainActivity::class.java))

    }

    fun darkClick(view:View){
        if(binding.switchDark.isChecked){
            binding.switchDark.isChecked=true
            binding.switchLight.isChecked=false
            ThemeDAO().updateTheme(dh,1,0)

        }
        else if(!binding.switchDark.isChecked){
            binding.switchDark.isChecked=false
            binding.switchLight.isChecked=true
            ThemeDAO().updateTheme(dh,0,0)
        }
        startActivity(Intent(this,SettingsActivity::class.java))

    }

    fun lightClick(view:View){
        if(binding.switchLight.isChecked){
            binding.switchDark.isChecked=false
            binding.switchLight.isChecked=true
            ThemeDAO().updateTheme(dh,0,0)

           }
        else if(!binding.switchLight.isChecked){
            binding.switchDark.isChecked=true
            binding.switchLight.isChecked=false
            ThemeDAO().updateTheme(dh,1,0)
        }
        startActivity(Intent(this,SettingsActivity::class.java))

    }

    fun deleteClick(){

        val dh=DataBaseHelper(this)
        NotesDAO().deleteNotes(dh)
        Toast.makeText(this,R.string.deleteSuccessfull,Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable {
            startActivity(Intent(this,MainActivity::class.java))
        }, 1000)

    }
}

