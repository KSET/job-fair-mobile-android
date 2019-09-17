package com.undabot.jobfair.networking.services

import com.undabot.jobfair.BoothsQuery
import com.undabot.jobfair.CompaniesQuery
import com.undabot.jobfair.CompanyDetailsQuery
import com.undabot.jobfair.CompanyResumeReviewMutation
import com.undabot.jobfair.CurrentUserQuery
import com.undabot.jobfair.EventReviewMutation
import com.undabot.jobfair.IndustriesQuery
import com.undabot.jobfair.LoginMutation
import com.undabot.jobfair.OnSiteRequestMutation
import com.undabot.jobfair.PresentationsQuery
import com.undabot.jobfair.ResetPasswordMutation
import com.undabot.jobfair.WorkshopsQuery
import com.undabot.jobfair.type.OnsiteRequestItemInput
import io.reactivex.Single

interface ResourceApiService {

    fun presentations(): Single<List<PresentationsQuery.Presentation>>

    fun workshops(): Single<List<WorkshopsQuery.Workshop>>

    fun companies(): Single<List<CompaniesQuery.Company>>

    fun industries(): Single<List<IndustriesQuery.Industry>>

    fun company(id: String): Single<CompanyDetailsQuery.Company>

    fun booths(): Single<List<BoothsQuery.Booth>>

    fun currentUser(): Single<CurrentUserQuery.Current_user?>

    fun resumeReview(
        clientMutationId: String,
        companyMemberId: String,
        resumeUid: String,
        notes: String,
        social: Int,
        ambition: Int,
        skill: Int
    ): Single<CompanyResumeReviewMutation.Data1>

    fun eventReview(
        presentationId: String?,
        workshopId: String?,
        value: Int
    ): Single<EventReviewMutation.Data1?>

    fun login(email: String, password: String): Single<LoginMutation.Login>

    fun resetPassword(email: String): Single<ResetPasswordMutation.Reset_password>

    fun onSiteRequest(companyId: String, items: List<OnsiteRequestItemInput>): Single<OnSiteRequestMutation.Onsite_request>

    fun clearCache()
}