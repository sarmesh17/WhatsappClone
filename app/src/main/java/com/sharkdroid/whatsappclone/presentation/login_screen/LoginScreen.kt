package com.sharkdroid.whatsappclone.presentation.login_screen

import android.app.Activity.RESULT_OK
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.sharkdroid.whatsappclone.R
import com.sharkdroid.whatsappclone.navigation.Routes
import com.sharkdroid.whatsappclone.viewmodels.LoginScreenViewModel
import androidx.compose.runtime.livedata.observeAsState


@Composable
fun LoginScreen(navController: NavHostController, loginScreenViewModel: LoginScreenViewModel) {

    var email by remember{
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    val intentSender by loginScreenViewModel.intentSenderLiveData.observeAsState()
    val loginSuccess by loginScreenViewModel.loginSuccessLiveData.observeAsState()
    val error by loginScreenViewModel.errorLiveData.observeAsState()



    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartIntentSenderForResult()) { result ->

            if (result.resultCode == RESULT_OK) {
                loginScreenViewModel.handleSignInResult(result.data)
            } else {
                Log.e("SignInFailure", "Sign-in failed with resultCode: ${result.resultCode}")
                result.data?.let {
                    val errorMessage = it.getStringExtra("error_message")
                    Log.e("SignInFailure", "Error Message: $errorMessage")
                    // Handle failure
                }

            }
        }

    LaunchedEffect(intentSender) {
        intentSender?.let {
            launcher.launch(IntentSenderRequest.Builder(it).build())
        }
    }

    LaunchedEffect(loginSuccess) {
        if (loginSuccess == true){
            navController.navigate(Routes.HomeScreen)
        }
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

        // sign-in button
        Row(modifier = Modifier.fillMaxWidth(0.8f), horizontalArrangement = Arrangement.End) {
            Text(
                text = "Click for Sign Up",
                fontSize = 16.sp,
                color = Color.DarkGray,
                modifier = Modifier.clickable {
                    navController.navigate(Routes.SignUpScreen)
                },
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                Log.d("Sign btn","Login executing")
                loginScreenViewModel.signInUser(email, password) { isSuccess, error ->
                    if (isSuccess) {
                        Log.d("Sign btn","Login successful")
                        navController.navigate(Routes.HomeScreen)
                    } else {
                        Toast.makeText(context, "$error", Toast.LENGTH_SHORT).show()
                        Log.d("Sign btn","Login failed = $error")

                    }

                }
            }, colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(
                    id = R.color.Dark_Teal
                )
            ), shape = RectangleShape, modifier = Modifier
                .clip(shape = RoundedCornerShape(16.dp))
                .size(150.dp, 60.dp)
        ) {
            Text(text = "SIGN IN", fontSize = 16.sp, color = Color.White)
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
                onClick = {
                    loginScreenViewModel.startSignIn();
                },
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
                    Text(text = "Google", color = Color.Black)
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


    }

}