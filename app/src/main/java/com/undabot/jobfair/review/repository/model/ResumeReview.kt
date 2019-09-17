package com.undabot.jobfair.review.repository.model

data class ResumeReview(
    val clientMutationId: String,
    val companyMemberId: String,
    val resumeUid: String,
    val notes: String,
    val social: Int,
    val ambition: Int,
    val skill: Int
)