package io.flexfi.app.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map

class DefaultLocalDataSource(private val dataStore: DataStore<Preferences>) {

    val userPreferences =
        dataStore.data
            .map {
                UserPreferences(
                    name = it[KEY_NAME] ?: return@map null,
                    token = it[KEY_TOKEN] ?: return@map null,
                    referralCode = it[KEY_REFERRAL_CODE] ?: return@map null,
                    positionInWaitlist = it[KEY_POSITION_IN_WAITLIST]
                )
            }
            .filterNotNull()

    suspend fun setName(name: String) {
        setValue(KEY_NAME, name)
    }

    suspend fun setToken(token: String) {
        setValue(KEY_TOKEN, token)
    }

    suspend fun setReferralCode(referralCode: String) {
        setValue(KEY_REFERRAL_CODE, referralCode)
    }

    suspend fun setPositionInWaitlist(position: Int) {
        setValue(KEY_POSITION_IN_WAITLIST, position)
    }

    private suspend fun <T> setValue(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    private companion object {
        val KEY_NAME = stringPreferencesKey("key:name")
        val KEY_TOKEN = stringPreferencesKey("key:token")
        val KEY_REFERRAL_CODE = stringPreferencesKey("key:referral_code")
        val KEY_POSITION_IN_WAITLIST = intPreferencesKey("key:position_in_waitlist")
    }
}
