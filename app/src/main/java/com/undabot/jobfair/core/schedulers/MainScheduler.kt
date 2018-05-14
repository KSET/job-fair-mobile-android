package com.undabot.jobfair.core.schedulers

import io.reactivex.Scheduler

class MainScheduler(private val scheduler: Scheduler) {
    fun get(): Scheduler = scheduler
}