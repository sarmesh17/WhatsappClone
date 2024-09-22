package com.sharkdroid.whatsappclone.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.sharkdroid.whatsappclone.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore:DataStore<Preferences> by preferencesDataStore("User_Setting")
class LocalUserManagerImp(
    private val context: Context
):LocalUserManager {
    override suspend fun readLoginEntry(): Flow<Boolean> {

        return  context.dataStore.data.map {
            it[PreferenceKey.LOGIN_ENTRY]?:false
        }
    }

    override suspend fun saveLoginEntry() {
        context.dataStore.edit {
            it[PreferenceKey.LOGIN_ENTRY]=true
        }
    }

}

private object PreferenceKey{

    val LOGIN_ENTRY = booleanPreferencesKey(name = "LoginEntry")

}