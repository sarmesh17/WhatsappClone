package com.sharkdroid.whatsappclone.domain.usecases

import com.sharkdroid.whatsappclone.domain.manager.LocalUserManager

class SaveLoginEntry(private val userManager: LocalUserManager) {


    suspend operator fun invoke (){

        userManager.saveLoginEntry()

    }
}