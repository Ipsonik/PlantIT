package com.example.plantit.features.login.data.remote.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.plantit.features.login.data.remote.network.model.UserResponse
import com.example.plantit.features.login.domain.AuthStorage
import kotlinx.coroutines.flow.first

class AuthStorageImpl (private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>) : AuthStorage {
    override suspend fun saveAccessToken(token: String) {
        dataStore.edit { it[ACCESS_TOKEN_KEY] = token }
    }

    override suspend fun saveRefreshToken(token: String) {
        dataStore.edit { it[REFRESH_TOKEN_KEY] = token }
    }

    override suspend fun saveUser(user: UserResponse?) {
        dataStore.edit {
            it[USER_ID_KEY] = user?.id ?: ""
            it[USER_EMAIL_KEY] = user?.email ?: ""
        }
    }

    override suspend fun getAccessToken(): String? {
        return dataStore.data.first()[ACCESS_TOKEN_KEY]
    }

    override suspend fun getUser(): UserResponse? {
        val prefs = dataStore.data.first()
        val id = prefs[USER_ID_KEY]
        val email = prefs[USER_EMAIL_KEY]
        return if (id != null && email != null) UserResponse(id, email) else null
    }

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }

    override suspend fun saveExpiresAt(timestamp: Long) {
        dataStore.edit { it[EXPIRES_AT_KEY] = timestamp }
    }

    override suspend fun getExpiresAt(): Long? {
        return dataStore.data.first()[EXPIRES_AT_KEY]
    }

    companion object StringKeys {
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
        private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
        private val USER_EMAIL_KEY = stringPreferencesKey("user_email")
        val EXPIRES_AT_KEY = longPreferencesKey("expires_at")

    }
}