package com.bmprj.inotes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import kotlinx.coroutines.*

@Suppress("DEPRECATION")
@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    val cs = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

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