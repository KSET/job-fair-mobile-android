package com.undabot.jobfair.home.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorCompat
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.undabot.jobfair.R
import com.undabot.jobfair.account.view.AccountActivity
import com.undabot.jobfair.booths.view.BoothsActivity
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseActivity
import com.undabot.jobfair.core.view.BaseFragment
import com.undabot.jobfair.home.adapters.HomePagerAdapter
import com.undabot.jobfair.home.di.HomeModule
import com.undabot.jobfair.login.models.User
import com.undabot.jobfair.login.models.UserType
import com.undabot.jobfair.login.view.LoginActivity
import com.undabot.jobfair.scanqr.view.ScanQrActivity
import com.undabot.jobfair.support.assistance.view.AssistanceActivity
import com.undabot.jobfair.support.drinks.view.DrinksActivity
import com.undabot.jobfair.support.submitcv.view.SubmitCvActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), HomeContract.View {

    companion object {
        private const val FAB_ANIMATION_DURATION = 300L
        private val FAB_ANIMATION_INTERPOLATOR = OvershootInterpolator()

        fun startWith(context: Context) {
            val intent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            context.startActivity(intent)
        }
    }

    @Inject lateinit var coordinator: HomeContract.Coordinator

    private val fragmentByPosition = LinkedHashMap<Int, BaseFragment>(5).apply {
        put(0, AgendaTab.fragmentFactory()!!)
        put(1, NewsTab.fragmentFactory()!!)
        put(2, CompaniesTab.fragmentFactory()!!)
        put(3, AboutTab.fragmentFactory()!!)
    }
    private val fragments: List<BaseFragment> = ArrayList(fragmentByPosition.values)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.inflateMenu(R.menu.account)
        toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_account) {
                coordinator.accountPressed()
            }
            fragmentByPosition[viewpager.currentItem]!!.onMenuItemClick(it)
        }

        coordinator.bind(this)
        coordinator.requestLoggedInUser()
        boothsButton.setOnClickListener { BoothsActivity.startWith(this) }
        setupViewPager()
        setupBottomNavigation()
        overlay.setOnClickListener {
            animateFab()
            animateOverlay()
        }
        setToolbarTitleFor(AgendaTab)
    }

    override fun showOptionsForUser(user: User?) {
        when (user?.type) {
            UserType.COMPANY -> {
                createFABItem(R.string.bottom_navigation_item_assistance, R.drawable.ic_assistance, this::assistanceClicked)
                createFABItem(R.string.bottom_navigation_item_drinks, R.drawable.ic_drinks, this::drinksClicked)
                createFABItem(R.string.bottom_navigation_item_scan_qr, R.drawable.ic_scan_qr, this::scanQrCodeClicked)
            }
            UserType.STUDENT -> {
                createFABItem(R.string.bottom_navigation_item_submit_or_edit_cv, R.drawable.ic_submit_cv, this::submitOrEditCVClicked)
            }
            UserType.ANONYMOUS, null -> {
                createFABItem(R.string.bottom_navigation_item_submit_cv, R.drawable.ic_submit_cv, this::submitCVClicked)
            }
        }

        fab.setOnClickListener {
            animateFab()
            animateOverlay()
        }
    }

    override fun openLoginScreen() {
        LoginActivity.startWith(this)
    }

    override fun openAccountScreen() {
        AccountActivity.startWith(this)
    }

    private fun setupViewPager() {
        viewpager.adapter = HomePagerAdapter(fragments, supportFragmentManager)
        viewpager.offscreenPageLimit = 4
    }

    private fun setupBottomNavigation() = with(bottomNavigation) {
        titleState = AHBottomNavigation.TitleState.ALWAYS_SHOW
        defaultBackgroundColor = Color.WHITE
        accentColor = ContextCompat.getColor(this@MainActivity, R.color.bottomNavigationActiveColor)
        inactiveColor = ContextCompat.getColor(this@MainActivity, R.color.bottomNavigationInactiveColor)
        isForceTint = true
        setUseElevation(false)

        addItems(Tab.getTabsWithEmpty().map { it.toNavigationItem(this@MainActivity) })
        disableItemAtPosition(2)
        setOnTabSelectedListener { position, wasSelected ->
            if (!wasSelected) {
                setToolbarTitleFor(position)

                var newPosition = position
                if (newPosition > 2) {
                    newPosition--
                }
                viewpager.currentItem = newPosition
            }
            true
        }
    }

    private fun createFABItem(@StringRes label: Int, @DrawableRes icon: Int, listener: () -> Unit): View {
        val view: ViewGroup = layoutInflater.inflate(R.layout.fab_item, overlay, false) as ViewGroup

        view.findViewById<TextView>(R.id.itemLabel).apply {
            text = resources.getString(label)
        }
        view.findViewById<ImageView>(R.id.itemButton).apply {
            setOnClickListener { listener() }
            setImageDrawable(ContextCompat.getDrawable(this@MainActivity, icon))
        }

        overlay.addView(view)

        return view
    }

    private fun assistanceClicked() {
        AssistanceActivity.startWith(this)
    }

    private fun drinksClicked() {
        DrinksActivity.startWith(this)
    }

    private fun scanQrCodeClicked() {
        ScanQrActivity.startWith(this)
    }

    private fun submitOrEditCVClicked() {
        SubmitCvActivity.startWith(this)
    }

    private fun submitCVClicked() {
        SubmitCvActivity.startWith(this)
    }

    private fun animateFab() {
        animate(
            view = fab,
            overlayVisibleListener = { rotation(0f) },
            overlayGoneListener = { rotation(45f) }
        )
    }

    private fun animateOverlay() {
        animate(
            view = overlay,
            overlayVisibleListener = {
                alpha(0f)
                withEndAction { overlay.visibility = View.GONE }
            },
            overlayGoneListener = {
                alpha(1f)
                overlay.visibility = View.VISIBLE
            }
        )
    }

    private inline fun animate(
        view: View,
        overlayVisibleListener: ViewPropertyAnimatorCompat.() -> Unit,
        overlayGoneListener: ViewPropertyAnimatorCompat.() -> Unit
    ) {
        view.animate().cancel()
        val animation = ViewCompat.animate(view)

        if (overlay.visibility == View.VISIBLE) {
            animation.overlayVisibleListener()
        } else {
            animation.overlayGoneListener()
        }

        animation
            .setDuration(FAB_ANIMATION_DURATION)
            .setInterpolator(FAB_ANIMATION_INTERPOLATOR)
            .start()
    }

    private fun setToolbarTitleFor(position: Int) {
        val tab = Tab.forPosition(position)
        if (tab != null) {
            setToolbarTitleFor(tab)
        }
    }

    private fun setToolbarTitleFor(tab: Tab) {
        toolbarTitle.text = getString(tab.title)
    }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(HomeModule()).inject(this)
    }
}
