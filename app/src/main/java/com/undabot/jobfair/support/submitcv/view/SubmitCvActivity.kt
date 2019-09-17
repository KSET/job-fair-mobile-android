package com.undabot.jobfair.support.submitcv.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.widget.Toolbar
import com.undabot.jobfair.R
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseActivity
import com.undabot.jobfair.networking.RESUME_SUBMIT_URL
import com.undabot.jobfair.support.submitcv.di.SubmitCvModule
import kotlinx.android.synthetic.main.submit_cv_activity.*
import kotlinx.android.synthetic.main.toolbar_details.*
import javax.inject.Inject


class SubmitCvActivity : BaseActivity(), SubmitCvContract.View {

    @Inject lateinit var coordinator: SubmitCvContract.Coordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.submit_cv_activity)
        setupToolbar()
        setupWebView()

        coordinator.bind(this)
        coordinator.tokenRequested()
    }

    override fun showEditResumeWebPage(token: String) {
        val urlWithToken = "$RESUME_SUBMIT_URL?auth_token=$token"
        webView.loadUrl(urlWithToken)
    }

    override fun showCreateResumeWebPage() {
        webView.loadUrl(RESUME_SUBMIT_URL)
    }

    override fun showError() {
        showGeneralErrorMessage()
    }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(SubmitCvModule()).inject(this)
    }

    private fun setupToolbar() {
        findViewById<Toolbar>(R.id.toolbar).apply {
            toolbarTitle.text = getString(R.string.submit_cv)
            setNavigationOnClickListener { super.onBackPressed() }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        clearCookies()
        webView.clearFormData()
        webView.settings.domStorageEnabled = true
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
    }

    companion object {
        fun startWith(context: Context) {
            val intent = Intent(context, SubmitCvActivity::class.java)
            context.startActivity(intent)
        }
    }

    private fun clearCookies() {
        CookieManager.getInstance().removeAllCookies(null)
        CookieManager.getInstance().flush()
    }
}