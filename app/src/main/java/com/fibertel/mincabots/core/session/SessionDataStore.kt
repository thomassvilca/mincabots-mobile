package com.fibertel.mincabots.core.session

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "mincabots_session")

data class SessionState(
    val isLoggedIn: Boolean = false,
    val userId: String? = null,
    val rol: String? = null
)

class SessionManager(private val context: Context) {

    private object Keys {
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        val USER_ID = stringPreferencesKey("user_id")
        val ROL = stringPreferencesKey("rol")
    }

    val sessionFlow: Flow<SessionState> = context.dataStore.data.map { prefs ->
        SessionState(
            isLoggedIn = prefs[Keys.IS_LOGGED_IN] ?: false,
            userId = prefs[Keys.USER_ID],
            rol = prefs[Keys.ROL]
        )
    }

    suspend fun saveSession(userId: String, rol: String) {
        context.dataStore.edit { prefs ->
            prefs[Keys.IS_LOGGED_IN] = true
            prefs[Keys.USER_ID] = userId
            prefs[Keys.ROL] = rol
        }
    }

    suspend fun clearSession() {
        context.dataStore.edit { prefs ->
            prefs[Keys.IS_LOGGED_IN] = false
            prefs.remove(Keys.USER_ID)
            prefs.remove(Keys.ROL)
        }
    }
}
