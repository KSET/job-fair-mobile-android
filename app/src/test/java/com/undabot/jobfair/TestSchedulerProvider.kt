package com.undabot.jobfair

import com.undabot.jobfair.core.schedulers.MainScheduler
import com.undabot.jobfair.core.schedulers.WorkerScheduler
import io.reactivex.schedulers.Schedulers

class TestSchedulerProvider {
    companion object {
        fun mainScheduler() = MainScheduler(Schedulers.trampoline())
        fun workerScheduler() = WorkerScheduler(Schedulers.trampoline())
    }
}