package com.undabot.jobfair.core.view

import java.lang.ref.WeakReference

abstract class AbsPresenter<V> : BasePresenter<V> {

    @Volatile private var view: WeakReference<V>? = null

    override fun view(): V? = view?.get()

    override fun bind(view: V) {
        if (this.view != null) {
            val previousView = this.view!!.get()

            if (previousView != null) {
                throw IllegalStateException(
                        "Previous view is not unbound! previousView: " + previousView)
            }
        }
        this.view = WeakReference(view)

        onBind()
    }

    override fun unbind(view: V) {
        val previousView = view()

        if (previousView == view) {
            this.view!!.clear()
            onUnbind()
        } else {
            throw IllegalStateException(
                    "Unexpected view! previous: $previousView, view to unbind: $view")
        }
    }

    fun onView(block: (V) -> Unit) {
        view()?.let { view -> block.invoke(view) }
    }

    protected fun onBind() {}

    protected fun onUnbind() {}
}
