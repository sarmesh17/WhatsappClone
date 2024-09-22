package com.sharkdroid.whatsappclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.google.firebase.FirebaseApp
import com.sharkdroid.whatsappclone.navigation.WhatsappCloneNavigator
import com.sharkdroid.whatsappclone.ui.theme.WhatsappCloneTheme
import com.sharkdroid.whatsappclone.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
   val viewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhatsappCloneTheme {
                FirebaseApp.initializeApp(this)
                val startDestination=viewModel.startDestination.value
              WhatsappCloneNavigator(startDestination)
            }
        }
    }
}

