package com.undabot.jobfair.splash.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.undabot.jobfair.home.view.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}