package com.undabot.jobfair.networking

import com.undabot.jobfair.login.repository.LoginPreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val loginPreferences: LoginPreferences
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val token = loginPreferences.getToken()
        val newRequest = if (!token.isNullOrBlank()) {
            request.newBuilder()
                .addHeader(HEADER_AUTHONRIZATION, "JWT $token")
                .build()
        } else {
            request
        }

        return chain.proceed(newRequest)
    }

    companion object {
        private const val HEADER_AUTHONRIZATION = "authorization"
    }
}