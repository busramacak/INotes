package com.bmprj.inotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

class SplashScreen : AppCompatActivity() {
    val cs = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        cs.launch {
            delay(1000)
            startActivity(Intent(this@SplashScreen,MainActivity::class.java))
            finish()
        }
    }

    override fun onPause() {
        cs.cancel()
        super.onPause()
    }
}