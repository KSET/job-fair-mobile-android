package com.undabot.jobfair.core.view

interface BaseCoordinator<in V> {

    fun bind(view: V)

    fun unbind(view: V)

    fun onBind()

    fun onUnbind()
}