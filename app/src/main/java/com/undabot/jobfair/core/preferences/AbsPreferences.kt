package com.undabot.jobfair.core.preferences

import android.content.SharedPreferences

abstract class AbsPreferences {

    abstract val prefs: SharedPreferences

    @Suppress("UNCHECKED_CAST")
    operator fun <R> get(preference: Preference<R>): R? =
        prefs.all.getOrElse(preference.name) { preference.default } as R?

    operator fun <R> set(key: Preference<R>, value: Any?) {
        val editor = prefs.edit()
        when (value) {
            is String? -> editor.putString(key.name, value)
            is Int -> editor.putInt(key.name, value)
            is Boolean -> editor.putBoolean(key.name, value)
            is Float -> editor.putFloat(key.name, value)
            is Long -> editor.putLong(key.name, value)
            is Set<*> -> editor.putSet(key.name, value)
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
        editor.apply()
    }

    @Suppress("UNCHECKED_CAST")
    private fun SharedPreferences.Editor.putSet(keyName: String, value: Set<*>) {
        try {
            putStringSet(keyName, value as Set<String>)
        } catch (exception: Exception) {
            throw UnsupportedOperationException("Not yet implemented")
        }
    }
}

open class Preference<R>(val name: String, val default: R?)