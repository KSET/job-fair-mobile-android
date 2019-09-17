package com.undabot.jobfair.login.repository.model

import com.google.gson.annotations.SerializedName
import com.undabot.jobfair.login.models.Resume

data class ResumeLocal(
    @SerializedName("id") val id: String,
    @SerializedName("uid") val uid: String
) {
    fun toResume(): Resume =
        Resume(
            id = id,
            uid = uid
        )

    companion object {
        fun fromResume(resume: Resume?) = resume?.run {
            ResumeLocal(
                id = id,
                uid = uid
            )
        }
    }
}
