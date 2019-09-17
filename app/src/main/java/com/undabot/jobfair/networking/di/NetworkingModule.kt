package com.undabot.jobfair.networking.di

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.cache.normalized.lru.EvictionPolicy
import com.apollographql.apollo.cache.normalized.lru.LruNormalizedCacheFactory
import com.undabot.jobfair.BuildConfig
import com.undabot.jobfair.core.di.scope.PerApplication
import com.undabot.jobfair.networking.API_URL
import com.undabot.jobfair.networking.AuthorizationInterceptor
import com.undabot.jobfair.networking.BASE_URL
import com.undabot.jobfair.networking.adapters.DateTimeCustomTypeAdapter
import com.undabot.jobfair.networking.services.ApiService
import com.undabot.jobfair.networking.services.ResourceApiService
import com.undabot.jobfair.networking.services.ResourceApiServiceImpl
import com.undabot.jobfair.type.CustomType
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

@Module
class NetworkingModule {

    @Provides
    @PerApplication
    fun okHttpClient(
        authorizationInterceptor: AuthorizationInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authorizationInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @PerApplication
    fun apolloCached(okHttpClient: OkHttpClient): ApolloClient {
        val cacheFactory = LruNormalizedCacheFactory(EvictionPolicy.builder().maxSizeBytes((100 * 1024).toLong()).build())
        return ApolloClient.builder()
            .serverUrl(API_URL)
            .normalizedCache(cacheFactory)
            .okHttpClient(okHttpClient)
            .addCustomTypeAdapter(CustomType.DATETIME, DateTimeCustomTypeAdapter())
            .build()!!
    }

    @Provides
    @PerApplication
    fun url(): HttpUrl = HttpUrl.parse(BASE_URL)!!

    @Provides
    @PerApplication
    fun retrofit(okHttpClient: OkHttpClient, apiUrl: HttpUrl): Retrofit {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(
                Persister(AnnotationStrategy())))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .client(okHttpClient)
        return retrofitBuilder.build()
    }

    @Provides
    @PerApplication
    fun apiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @PerApplication
    fun resourceApiService(apolloClient: ApolloClient): ResourceApiService =
        ResourceApiServiceImpl(apolloClient)

    @Provides
    @PerApplication
    fun loggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            )
    }
}