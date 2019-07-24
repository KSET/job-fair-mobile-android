package com.undabot.jobfair.splash.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.undabot.jobfair.R
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseActivity
import com.undabot.jobfair.home.view.MainActivity
import com.undabot.jobfair.login.view.LoginActivity
import com.undabot.jobfair.splash.di.SplashModule
import kotlinx.android.synthetic.main.splash_activity.*
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashContract.View {

    companion object {
        fun startWith(context: Context) {
            val intent = Intent(context, SplashActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            context.startActivity(intent)
        }
    }

    @Inject lateinit var coordinator: SplashContract.Coordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coordinator.bind(this)
        coordinator.initializeApp()
    }

    override fun openLoginScreen() {
        LoginActivity.startWith(this)
    }

    override fun openHomeScreen() {
        MainActivity.startWith(this)
    }

    override fun showLoginTeaser() {
        setContentView(R.layout.splash_activity)
        // Drawable is too large to set it with 'src' attribute
        Glide.with(this)
            .load(R.drawable.splash_background_icons)
            .centerCrop()
            .into(background_icons)
        loginButton.setOnClickListener { coordinator.requestLogin() }
        skipButton.setOnClickListener { coordinator.requestSkipLogin() }
    }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(SplashModule()).inject(this)
    }
}