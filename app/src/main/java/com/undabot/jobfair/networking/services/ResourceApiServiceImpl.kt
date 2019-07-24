package com.undabot.jobfair.networking.services

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.cache.http.HttpCachePolicy
import com.apollographql.apollo.rx2.Rx2Apollo
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

class ResourceApiServiceImpl(private val apolloClient: ApolloClient) : ResourceApiService {

    override fun industries(): Single<List<IndustriesQuery.Industry>> =
        Rx2Apollo.from(apolloClient.query(IndustriesQuery.builder().build())
            .httpCachePolicy(HttpCachePolicy.CACHE_FIRST))
            .singleOrError()
            .map { it.data()?.industries().orEmpty() }

    override fun company(id: String): Single<CompanyDetailsQuery.Company> =
        Rx2Apollo.from(apolloClient.query(CompanyDetailsQuery.builder().id(id).build())
            .httpCachePolicy(HttpCachePolicy.CACHE_FIRST))
            .singleOrError()
            .map { it.data()?.company() }

    override fun companies(): Single<List<CompaniesQuery.Company>> =
        Rx2Apollo.from(apolloClient.query(CompaniesQuery.builder().build())
            .httpCachePolicy(HttpCachePolicy.CACHE_FIRST))
            .singleOrError()
            .map { it.data()?.companies().orEmpty() }

    override fun presentations(): Single<List<PresentationsQuery.Presentation>> =
        Rx2Apollo.from(apolloClient.query(PresentationsQuery.builder().build()))
            .singleOrError()
            .map { it.data()?.presentations().orEmpty() }

    override fun workshops(): Single<List<WorkshopsQuery.Workshop>> =
        Rx2Apollo.from(apolloClient.query(WorkshopsQuery.builder().build()))
            .singleOrError()
            .map { it.data()?.workshops().orEmpty() }

    override fun booths(): Single<List<BoothsQuery.Booth>> =
        Rx2Apollo.from(apolloClient.query(BoothsQuery.builder().build()))
            .singleOrError()
            .map { it.data()?.booths().orEmpty() }

    override fun currentUser(): Single<CurrentUserQuery.Current_user?> =
        Rx2Apollo.from(apolloClient.query(CurrentUserQuery.builder().build()))
            .singleOrError()
            .map { it.data()?.current_user() }

    override fun resumeReview(
        clientMutationId: String,
        companyMemberId: String,
        resumeUid: String,
        notes: String,
        social: Int,
        ambition: Int,
        skill: Int
    ): Single<CompanyResumeReviewMutation.Data1> =
        Rx2Apollo.from(
            apolloClient.mutate(
                CompanyResumeReviewMutation.builder()
                    .clientMutationId(clientMutationId)
                    .company_member_id(companyMemberId)
                    .resume_uid(resumeUid)
                    .notes(notes)
                    .social(social)
                    .ambition(ambition)
                    .skill(skill)
                    .build()
            )
        )
            .singleOrError()
            .map { it.data()?.company_resume_review()?.data() }

    override fun eventReview(presentationId: String?,
                             workshopId: String?,
                             value: Int): Single<EventReviewMutation.Data1?> =
        Rx2Apollo.from(apolloClient.mutate(
            EventReviewMutation.builder()
                .presentationId(presentationId)
                .workshopId(workshopId)
                .value(value)
                .build()))
            .singleOrError()
            .map { it.data()?.event_review()?.data() }

    override fun login(email: String, password: String): Single<LoginMutation.Login> =
        Rx2Apollo.from(apolloClient.mutate(
            LoginMutation.builder()
                .email(email)
                .password(password).build()))
            .singleOrError()
            .map { it.data()?.login() }

    override fun resetPassword(email: String): Single<ResetPasswordMutation.Reset_password> =
        Rx2Apollo.from(apolloClient.mutate(
            ResetPasswordMutation.builder()
                .email(email).build()
        )).singleOrError()
            .map { it.data()?.reset_password() ?: ResetPasswordMutation.Reset_password("", false) }

    override fun onSiteRequest(
        companyId: String,
        items: List<OnsiteRequestItemInput>
    ): Single<OnSiteRequestMutation.Onsite_request> =
        Rx2Apollo.from(
            apolloClient.mutate(
                OnSiteRequestMutation.builder()
                    .companyId(companyId)
                    .items(items)
                    .build()))
            .singleOrError()
            .map { it.data()?.onsite_request() ?: OnSiteRequestMutation.Onsite_request("", null) }

    override fun clearCache() {
        apolloClient.clearHttpCache()
        apolloClient.clearNormalizedCache()
    }
}