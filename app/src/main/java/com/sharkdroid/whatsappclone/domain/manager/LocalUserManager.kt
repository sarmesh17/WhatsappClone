package com.sharkdroid.whatsappclone.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {

    suspend fun readLoginEntry():Flow<Boolean>

    suspend fun saveLoginEntry()
}