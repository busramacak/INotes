package com.bmprj.inotes

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.coroutines.*

@Suppress("DEPRECATION")
@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    val cs = CoroutineScope(Dispatchers.Main)
    val dh=DataBaseHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val themeList = ThemeDAO().getTheme(dh)


        if(themeList.size==0){
            val nightModeFlags: Int = this.resources.configuration.uiMode and
                    Configuration.UI_MODE_NIGHT_MASK
            when (nightModeFlags) {
                Configuration.UI_MODE_NIGHT_YES -> {
                    ThemeDAO().addTheme(dh,1,0)

                }
                Configuration.UI_MODE_NIGHT_NO -> {
                    ThemeDAO().addTheme(dh,0,0)

                }
            }

        }
        else if(themeList.get(0).theme_id==0 && themeList.get(0).theme_is_dark==1){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        }

        window.setFlags(
            FLAG_FULLSCREEN,
            FLAG_FULLSCREEN
        )

        cs.launch {
            delay(3000)
            startActivity(Intent(this@SplashScreen,MainActivity::class.java))
            finish()
        }

    }

    override fun onPause() {
        cs.cancel()
        super.onPause()
    }
}