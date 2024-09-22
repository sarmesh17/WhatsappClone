package com.sharkdroid.whatsappclone.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class SettingScreenViewModel @Inject constructor() : ViewModel() {

    private val _profileImageUri = MutableStateFlow<Uri?>(null)
    val profileImageUri: StateFlow<Uri?> get() = _profileImageUri

    private val _profileImageUrl = MutableStateFlow<String?>(null)
    val profileImageUrl: StateFlow<String?> get() = _profileImageUrl

    fun setProfileImageUri(uri: Uri) {
        _profileImageUri.value = uri
        uploadImageTiFirebaseStorage(uri)
    }

    private fun uploadImageTiFirebaseStorage(uri: Uri) {
        val storageReference =
            Firebase.storage.reference.child("profileImages/${UUID.randomUUID()}")
        val uploadTask = storageReference.putFile(uri)

        uploadTask.addOnSuccessListener {
            storageReference.downloadUrl.addOnSuccessListener { downloadUrl ->

                _profileImageUrl.value = downloadUrl.toString()
                saveProfileImageUrl(downloadUrl.toString())

            }
        }.addOnFailureListener {

            // Handle any errors

        }
    }

    private fun saveProfileImageUrl(downloadUrl: String) {

        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val userRef = FirebaseFirestore.getInstance().collection("users").document(userId)

        viewModelScope.launch {
            userRef.update("profileImageUrl", downloadUrl)
                .addOnSuccessListener {
                    // Profile image URL successfully saved

                }
                .addOnFailureListener {
                    // Handle the error
                }
        }

    }

    fun fetchProfileImageUrl(){

        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val userRef = FirebaseFirestore.getInstance().collection("users").document(userId)

        viewModelScope.launch {
            userRef.get().addOnSuccessListener { document ->
                val profileImageUrl = document.getString("profileImageUrl")
                _profileImageUrl.value=profileImageUrl
            }
        }

    }


}