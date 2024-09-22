package com.sharkdroid.whatsappclone.presentation.settings_screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.sharkdroid.whatsappclone.R
import com.sharkdroid.whatsappclone.viewmodels.SettingScreenViewModel

@Composable
fun SettingsScreen(settingScreenViewModel: SettingScreenViewModel) {

    val profileImageUri by settingScreenViewModel.profileImageUri.collectAsState()
    val profileImageUrl by settingScreenViewModel.profileImageUrl.collectAsState()

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let { settingScreenViewModel.setProfileImageUri(uri) }
        }

    LaunchedEffect(Unit) {
        settingScreenViewModel.fetchProfileImageUrl()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.Dark_Teal))
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Box(
            modifier = Modifier
                .size(135.dp)
        ) {
            profileImageUri?.let {

                Image(
                    painter = rememberAsyncImagePainter(model = it),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .fillMaxSize()
                        .border(3.dp, color = Color.Black, shape = CircleShape),
                    contentScale = ContentScale.Crop

                )
            }?: profileImageUrl.let {
                Image(
                    painter = rememberAsyncImagePainter(model = it),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .fillMaxSize()
                        .border(3.dp, color = Color.Black, shape = CircleShape),
                         contentScale = ContentScale.Crop
                    )
            }

            FloatingActionButton(
                onClick = { launcher.launch("image/*") },
                containerColor = colorResource(id = R.color.Sky_Blue).copy(alpha = 1.0f),
                shape = CircleShape, modifier = Modifier
                    .size(45.dp)
                    .align(alignment = Alignment.BottomEnd)

            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = "User Name:",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = {
                        Text(text = "Your Name", color = Color.White)
                    },
                    colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent),
                    modifier = Modifier.padding(end = 10.dp)
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {

                Text(
                    text = "About:",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(45.dp))
                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = {
                        Text(text = "About", color = Color.White)
                    },
                    colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent),
                    modifier = Modifier.padding(end = 10.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp), horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White, contentColor = colorResource(
                            id = R.color.Dark_Teal
                        )
                    ), shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "SAVE")
                }
            }

        }
        Spacer(modifier = Modifier.height(100.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 60.dp, topEnd = 60.dp)
                )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 100.dp),
                verticalArrangement = Arrangement.Center,
            ) {

                Row (verticalAlignment = Alignment.CenterVertically){

                    Icon(imageVector = Icons.Filled.Lock, contentDescription = null, tint = colorResource(
                        id = R.color.Dark_Teal
                    ), modifier = Modifier.size(35.dp) )
                     Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "Privacy Policy", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row (verticalAlignment = Alignment.CenterVertically){

                    Icon(imageVector = Icons.Filled.Info, contentDescription = null, tint = colorResource(
                        id = R.color.Dark_Teal
                    ), modifier = Modifier.size(35.dp) )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "About Us", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row (verticalAlignment = Alignment.CenterVertically){

                    Icon(painter = painterResource(id = R.drawable.share_icon), contentDescription = null, tint = colorResource(
                        id = R.color.Dark_Teal
                    ), modifier = Modifier.size(35.dp) )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "Invite a Friend", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row (verticalAlignment = Alignment.CenterVertically){

                    Icon(imageVector = Icons.Filled.Notifications, contentDescription = null, tint = colorResource(
                        id = R.color.Dark_Teal
                    ), modifier = Modifier.size(35.dp) )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "Notification", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(12.dp))

                Row (verticalAlignment = Alignment.CenterVertically){

                    Icon(painter = painterResource(id = R.drawable.help), contentDescription = null, tint = colorResource(
                        id = R.color.Dark_Teal
                    ), modifier = Modifier.size(35.dp) )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "Help", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }

            }



        }

    }
}

