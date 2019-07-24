package com.undabot.jobfair.core.view

open class AbsCoordinator<V, out P : BasePresenter<V>>(
    protected val presenter: P
) : BaseCoordinator<V> {

    override fun bind(view: V) {
        presenter.bind(view)
        onBind()
    }

    override fun unbind(view: V) {
        presenter.unbind(view)
        onUnbind()
    }

    override fun onBind() {}

    override fun onUnbind() {}
}