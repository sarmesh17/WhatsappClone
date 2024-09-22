package com.sharkdroid.whatsappclone.navigation

import kotlinx.serialization.Serializable

sealed class Routes {

    @Serializable
    data object LoginScreen:Routes()

    @Serializable
    data object SignUpScreen:Routes()

    @Serializable
    data object HomeScreen:Routes()

    @Serializable
    data object SettingScreen:Routes()

    @Serializable
    data object GroupChatScreen:Routes()



}