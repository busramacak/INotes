package com.bmprj.inotes.view.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AlertDialog.Builder
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bmprj.inotes.R
import com.bmprj.inotes.data.DataBaseHelper
import com.bmprj.inotes.data.dao.NotesDAO
import com.bmprj.inotes.databinding.ActivitySettingsBinding
import com.bmprj.inotes.viewmodel.SettingsViewModel


@Suppress("DEPRECATION")
class SettingsActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySettingsBinding
    private lateinit var viewModel: SettingsViewModel
    private val dh= DataBaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_settings)
        binding.settingsDesign=this
        viewModel=ViewModelProvider(this)[SettingsViewModel::class.java]

        viewModel.getTheme(dh)



        observeLiveData()

    }

    private fun observeLiveData(){
        viewModel.setting.observe(this){theme->
            theme?.let{
                if(theme.theme_id==0 && theme.theme_is_dark==1){
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

        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun backClick(){
        startActivity(Intent(this, MainActivity::class.java))

    }

    fun darkClick(){


        if(binding.switchDark.isChecked){
            viewModel.setTheme(dh,1,0)

        }
        else if(!binding.switchDark.isChecked){
            viewModel.setTheme(dh,0,0)
        }
    }

    fun lightClick(){
        if(binding.switchLight.isChecked){
            viewModel.setTheme(dh,0,0)

           }
        else if(!binding.switchLight.isChecked){
            viewModel.setTheme(dh,1,0)
        }
    }

    fun deleteClick(){

        val dialogClickListener =
            DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        viewModel.deleteAllNotes(dh,this)
                        Handler().postDelayed({
                            startActivity(Intent(this, MainActivity::class.java))
                        }, 1000)
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {}
                }
            }

        val builder = Builder(this)
        builder.setMessage(R.string.areYouSure)
            .setPositiveButton(R.string.delete, dialogClickListener)
            .setNegativeButton(R.string.noDel, dialogClickListener)
            .show()

    }
}

