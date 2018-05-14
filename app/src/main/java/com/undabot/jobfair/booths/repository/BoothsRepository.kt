package com.undabot.jobfair.booths.repository

import io.reactivex.Single

interface BoothsRepository {
    fun booths(): Single<BoothsResult>
}