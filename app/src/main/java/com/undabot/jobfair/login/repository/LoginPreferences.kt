package com.undabot.jobfair.login.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import com.undabot.jobfair.core.preferences.AbsPreferences
import com.undabot.jobfair.core.preferences.Preference
import com.undabot.jobfair.login.models.User
import com.undabot.jobfair.login.repository.model.UserLocal

class LoginPreferences(
    context: Context,
    private val gson: Gson
) : AbsPreferences() {

    override val prefs: SharedPreferences = context.getSharedPreferences("login-preferences", MODE_PRIVATE)

    fun setToken(token: String?) {
        set(TokenPreference, token)
    }

    fun setUser(user: User?) {
        var json: String? = null
        UserLocal.fromUser(user)?.let { userLocal ->
            json = gson.toJson(userLocal)
        }
        set(UserPreference, json)
    }

    fun getToken(): String? = get(TokenPreference)

    fun getUser(): User? {
        val json: String? = get(UserPreference)
        return json?.let {
            val userLocal = gson.fromJson(json, UserLocal::class.java)
            userLocal.toUser()
        }
    }

    private object TokenPreference : Preference<String>("token", null)
    private object UserPreference : Preference<String>("user", null)
}
