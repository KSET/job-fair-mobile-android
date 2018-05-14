package com.undabot.jobfair.networking.services

import BoothsQuery
import CompaniesQuery
import CompanyDetailsQuery
import IndustriesQuery
import PresentationsQuery
import WorkshopsQuery
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.cache.http.HttpCachePolicy
import com.apollographql.apollo.rx2.Rx2Apollo
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
}