package com.sharkdroid.whatsappclone.domain.usecases

import com.sharkdroid.whatsappclone.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadLoginEntry(private val userManager: LocalUserManager) {

    suspend operator fun invoke (): Flow<Boolean> {

        return userManager.readLoginEntry()

    }
}