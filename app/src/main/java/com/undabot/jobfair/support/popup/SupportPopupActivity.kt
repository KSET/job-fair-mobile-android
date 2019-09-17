package com.undabot.jobfair.support.popup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.undabot.jobfair.R
import kotlinx.android.synthetic.main.support_popup_activity.*

class SupportPopupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.support_popup_activity)
        setupToolbar()

        intent.getCharSequenceExtra(DRINKS_EXTRA)?.let { drinks ->
            handleDrinks(drinks)
        }
        intent.getCharSequenceExtra(ASSISTANCE_EXTRA)?.let { assistance ->
            handleAssistance(assistance)
        }
    }

    private fun handleDrinks(drinks: CharSequence) {
        icon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_macchiato))
        message1.text = getString(R.string.support_popup_drinks_success_message)
        message2.text = getString(R.string.support_popup_you_ordered)
        support_content.text = drinks
    }

    private fun handleAssistance(assistance: CharSequence) {
        icon.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_assistance_big))
        message1.text = getString(R.string.support_popup_assistance_success_message)
        message2.text = getString(R.string.support_popup_you_asked_for)
        support_content.text = assistance
    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { super.onBackPressed() }
    }

    companion object {
        private const val DRINKS_EXTRA = "drinks"
        private const val ASSISTANCE_EXTRA = "assistance"

        fun startForDrinksWith(context: Context, drinks: CharSequence?) {
            val intent = Intent(context, SupportPopupActivity::class.java).apply {
                putExtra(DRINKS_EXTRA, drinks)
            }
            context.startActivity(intent)
        }

        fun startForAssistanceWith(context: Context, note: CharSequence?) {
            val intent = Intent(context, SupportPopupActivity::class.java).apply {
                putExtra(ASSISTANCE_EXTRA, note)
            }
            context.startActivity(intent)
        }
    }
}