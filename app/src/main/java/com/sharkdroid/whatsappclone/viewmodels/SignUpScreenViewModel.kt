package com.sharkdroid.whatsappclone.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.sharkdroid.whatsappclone.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    @Named("root") private val rootReference: DatabaseReference,
):ViewModel() {

    private  var _isSignedUp=MutableStateFlow<Boolean>(true)
    val isSignedUp=_isSignedUp.asStateFlow()

    private var _errorMessage by mutableStateOf("")
    val errorMessage:String get() =_errorMessage

  fun signUpUser(name:String,email:String,password:String){

      firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{ task ->

          if (task.isSuccessful){
              val userId=firebaseAuth.currentUser?.uid?: return@addOnCompleteListener
              val user= User(name, email, password)


              rootReference.child("users").child(userId).setValue(user).addOnCompleteListener{databaseTask ->
                  if (databaseTask.isSuccessful){
                      Log.d("SignUp","state:False")
                      _isSignedUp.value=true
                      Log.d("SignUp","$_isSignedUp")
                  }
              }
          }else{

              _errorMessage=task.exception.toString()

          }
      }

  }


}