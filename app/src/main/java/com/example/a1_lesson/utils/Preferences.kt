package com.example.a1_lesson.utils
import android.content.Context
import android.net.Uri

class Preferences(private val context: Context) {


    companion object {
        const val ON_BOARDING_STATE = "have seen on boarding"
        const val PROFILE_TITLE_STATE_KEY = "saved text"
        const val PROFILE_PICTURE_KEY = "picture state"
    }

    private val prefs = context.getSharedPreferences(
        "utils",
        Context.MODE_PRIVATE
    )

    fun setHaveSeenOnBoarding() {
        prefs.edit().putBoolean(ON_BOARDING_STATE, true).apply()
    }

    fun getHaveSeenOnBoarding() = prefs.getBoolean(ON_BOARDING_STATE, false)

    fun saveProfilePicture(profileUri: Uri) {
        prefs.edit().putString(PROFILE_PICTURE_KEY, profileUri.toString()).apply()
    }

    fun getProfilePicture() : Uri?{
        val uriString = prefs.getString(PROFILE_PICTURE_KEY, null)
        return if (uriString != null) Uri.parse(uriString)
        else null
    }

    fun saveProfileName(str: String) {
        prefs.edit().putString(PROFILE_TITLE_STATE_KEY, str).apply()
    }

    fun getProfileName() = prefs.getString(PROFILE_TITLE_STATE_KEY, null)
}