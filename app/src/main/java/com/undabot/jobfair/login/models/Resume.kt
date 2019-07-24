package com.undabot.jobfair.login.models

data class Resume(
    val id: String,
    val uid: String
) {
    companion object {
        val NULL_RESUME = Resume(
            id = "",
            uid = ""
        )
    }
}
