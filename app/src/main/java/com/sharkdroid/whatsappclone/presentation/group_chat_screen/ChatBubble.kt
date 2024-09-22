package com.sharkdroid.whatsappclone.presentation.group_chat_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sharkdroid.whatsappclone.R
import com.sharkdroid.whatsappclone.models.ChatMessage

@Composable
fun ChatBubble(chatMessage: ChatMessage) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), contentAlignment = Alignment.TopEnd
    ) {

        Box(
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.Mint_Green),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp)
        ){

            Text(text = chatMessage.message, color = colorResource(id = R.color.black))


        }
    }
}

