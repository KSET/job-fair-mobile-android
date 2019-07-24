package com.undabot.jobfair.networking

import com.undabot.jobfair.BuildConfig

const val BASE_URL = BuildConfig.CLIENT_URL
const val API_URL = BuildConfig.CLIENT_URL.plus("api/v2/graphql/")
const val RESUME_SUBMIT_URL = BuildConfig.CLIENT_URL.plus("hr/zivotopisi/novo")