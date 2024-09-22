package com.sharkdroid.whatsappclone.presentation.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sharkdroid.whatsappclone.R
import com.sharkdroid.whatsappclone.presentation.top_app_bar.TopBar

@Composable
fun HomeScreen(navHostController: NavHostController) {

    var selectedIndex by remember {
        mutableStateOf(WhatsappTab.CHATS)
    }

    Scaffold(
        topBar = {
            TopBar(navHostController)
        }
    ) {
        Column(modifier = Modifier.padding(it)) {

            TabRow(selectedTabIndex = selectedIndex.ordinal, containerColor = colorResource(
                id = R.color.Dark_Teal
            ), contentColor = Color.White,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedIndex.ordinal]),
                        color = Color.White // Set the indicator color to green here
                    )
                }
            ) {
                // tab for chats
                Tab(
                    selected = selectedIndex == WhatsappTab.CHATS,
                    onClick = { selectedIndex = WhatsappTab.CHATS },
                    modifier = Modifier.height(55.dp)
                ) {
                    Text(text = "CHATS")
                }

                Tab(
                    selected = selectedIndex == WhatsappTab.STATUS,
                    onClick = { selectedIndex = WhatsappTab.STATUS }
                ) {
                    Text(text = "STATUS")
                }

                Tab(
                    selected = selectedIndex == WhatsappTab.CALLS,
                    onClick = { selectedIndex = WhatsappTab.CALLS }) {
                    Text(text = "CALLS")
                }

            }

            when (selectedIndex) {

                WhatsappTab.CHATS -> ChatScreen()
                WhatsappTab.STATUS -> StatusScreen()
                WhatsappTab.CALLS -> CallsScreen()


            }
        }


    }
}

@Composable
fun CallsScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Hello World")
    }
}

@Composable
fun StatusScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "No Status available")
    }

}

@Composable
fun ChatScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "No chat history available")
    }

}

enum class WhatsappTab {

    CHATS, STATUS, CALLS
}


