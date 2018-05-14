package com.undabot.jobfair.core.schedulers

import io.reactivex.Scheduler

class WorkerScheduler(private val scheduler: Scheduler) {
    fun get(): Scheduler = scheduler
}