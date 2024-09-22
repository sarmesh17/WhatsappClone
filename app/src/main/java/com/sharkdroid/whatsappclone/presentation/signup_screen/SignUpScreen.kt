package com.sharkdroid.whatsappclone.presentation.signup_screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sharkdroid.whatsappclone.R
import com.sharkdroid.whatsappclone.navigation.Routes
import com.sharkdroid.whatsappclone.viewmodels.SignUpScreenViewModel

@Composable
fun SignUpScreen(
     signUpScreenViewModel: SignUpScreenViewModel,
     navHostController: NavHostController

) {


    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    val context= LocalContext.current

    val isSignedUp by remember {
        mutableStateOf(signUpScreenViewModel.isSignedUp.value)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        Image(
            painter = painterResource(id = R.drawable.whatsapp_logo),
            contentDescription = null,
            modifier = Modifier.size(110.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column {

            // name-field
            TextField(
                value = name,
                onValueChange = {
                     name = it
                },
                placeholder = {
                    Text(text = "Your Name", color = Color.Gray)
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)

            )

            Spacer(modifier = Modifier.height(16.dp))

            // email text-field
            TextField(
                value = email,
                onValueChange = {
                    email = it
                },
                placeholder = {
                    Text(text = "Email", color = Color.Gray)
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)

            )
            Spacer(modifier = Modifier.height(16.dp))

            // password-field

            TextField(
                value = password,
                onValueChange = {
                    password = it
                },
                placeholder = {
                    Text(text = "password", color = Color.Gray)
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)

            )

        }
        Spacer(modifier = Modifier.height(12.dp))

        // sign-up button
        Row(modifier = Modifier.fillMaxWidth(0.8f), horizontalArrangement = Arrangement.End) {
            Text(text = "Already have account ", fontSize = 16.sp, color = Color.DarkGray, modifier = Modifier.clickable { navHostController.navigate(Routes.LoginScreen) })
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { signUpScreenViewModel.signUpUser(name, email, password);
                if (isSignedUp){
                Log.d("signUpScreen success","state: ${signUpScreenViewModel.isSignedUp.value}")
                navHostController.navigate(Routes.LoginScreen)
            }else{
                    Log.d("signUpScreen failed","state: ${signUpScreenViewModel.isSignedUp.value}")

                    Toast.makeText(context, signUpScreenViewModel.errorMessage, Toast.LENGTH_SHORT).show()
            }
            }, colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(
                    id = R.color.Dark_Teal
                )
            ), shape = RectangleShape, modifier = Modifier
                .clip(shape = RoundedCornerShape(16.dp))
                .size(150.dp, 60.dp)
        ) {
            Text(text = "SIGN UP", fontSize = 16.sp)
        }
        Spacer(modifier = Modifier.height(24.dp))
        // google and facebook login button

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp), horizontalArrangement = Arrangement.Center
        ) {

            // google-btn
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(180.dp, 60.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // google icon
                    Image(
                        painter = painterResource(id = R.drawable.google_logo),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        alignment = Alignment.TopStart
                    )

                    Spacer(modifier = Modifier.width(35.dp))
                    // google-text
                    Text(text = "Google", color = Color.Black,)
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            // facebook-btn
            Button(
                onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(
                        id = R.color.Bright_Blue
                    )
                ), shape = RoundedCornerShape(8.dp), modifier = Modifier.size(180.dp, 60.dp)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // facebook icon
                    Image(
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )

                    Spacer(modifier = Modifier.width(35.dp))
                    // facebook-text
                    Text(text = "Google", color = Color.Black)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Sign up with Phone", fontSize = 20.sp, color = colorResource(id = R.color.Dark_Teal))


    }
}