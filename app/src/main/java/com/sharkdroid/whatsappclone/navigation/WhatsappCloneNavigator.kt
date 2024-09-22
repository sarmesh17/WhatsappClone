package com.sharkdroid.whatsappclone.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sharkdroid.whatsappclone.presentation.group_chat_screen.GroupChatScreen
import com.sharkdroid.whatsappclone.presentation.homescreen.HomeScreen
import com.sharkdroid.whatsappclone.presentation.login_screen.LoginScreen
import com.sharkdroid.whatsappclone.presentation.settings_screen.SettingsScreen
import com.sharkdroid.whatsappclone.presentation.signup_screen.SignUpScreen
import com.sharkdroid.whatsappclone.viewmodels.GroupChatViewModel
import com.sharkdroid.whatsappclone.viewmodels.LoginScreenViewModel
import com.sharkdroid.whatsappclone.viewmodels.SettingScreenViewModel
import com.sharkdroid.whatsappclone.viewmodels.SignUpScreenViewModel

@Composable
fun WhatsappCloneNavigator(startDestination: Routes) {

    val navController= rememberNavController()

    NavHost(navController = navController, startDestination = startDestination ) {

        composable<Routes.LoginScreen> {
            val viewModel:LoginScreenViewModel= hiltViewModel()
            LoginScreen(navController,viewModel)
        }

        composable<Routes.SignUpScreen> {
            val viewModel:SignUpScreenViewModel= hiltViewModel()
            SignUpScreen(viewModel,navController)
        }

        composable<Routes.GroupChatScreen> {
            val viewModel:GroupChatViewModel= hiltViewModel()
            GroupChatScreen(viewModel = viewModel )
        }

        composable<Routes.SettingScreen> {
            val viewModel:SettingScreenViewModel= hiltViewModel()
            SettingsScreen(viewModel)
        }

        composable<Routes.HomeScreen> {
            HomeScreen(navController)
        }
        composable<Routes.GroupChatScreen> {
            val viewModel:GroupChatViewModel= hiltViewModel()
            GroupChatScreen(viewModel = viewModel)
        }

    }


}