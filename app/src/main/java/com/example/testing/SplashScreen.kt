package com.example.testing

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import com.example.testing.View.Login

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash_screen)


        initialization()
    }

    private fun initialization() {
        Handler().postDelayed(Runnable {
            val intent = Intent(
                this, MainActivity::class.java
            )
            startActivity(intent)
            finish()
        }, 100)
    }
}