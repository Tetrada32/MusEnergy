package com.gahov.musenergy.data.local.storage.authorization

import android.content.SharedPreferences
import com.gahov.musenergy.data.common.BasePreferences

class ImplAuthorizationLocalStorage(
    preferences: SharedPreferences,
) : AuthorizationLocalStorage, BasePreferences(preferences) {

    override var accessToken: String
        get() = get(KEY_ACCESS_TOKEN, "")
        set(value) = put(KEY_ACCESS_TOKEN, value)

    companion object {
        private const val KEY_STATE = "key_auth_state"
        private const val KEY_ACCESS_TOKEN = "key_access_token"
        private const val KEY_REFRESH_TOKEN = "key_refresh_token"
        private const val KEY_EXPIRED = "key_auth_expired_date"
        private const val KEY_LAST_REFRESH = "key_last_refresh"
    }
}