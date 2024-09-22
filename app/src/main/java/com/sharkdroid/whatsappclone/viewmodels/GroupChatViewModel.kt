package com.sharkdroid.whatsappclone.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.sharkdroid.whatsappclone.models.ChatMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class GroupChatViewModel @Inject constructor(
    @Named("chats") private val chatRef: DatabaseReference
) : ViewModel() {

    private val _chatMessage = MutableStateFlow<List<ChatMessage>>(emptyList())
    val chatMessage: StateFlow<List<ChatMessage>> = _chatMessage

    init {
        listenForMessage()
    }

    private fun listenForMessage() {
        chatRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val chatMessage = snapshot.getValue(ChatMessage::class.java)
                if (chatMessage != null) {

                    viewModelScope.launch {
                        _chatMessage.value += chatMessage
                    }
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {

            }

            override fun onChildRemoved(snapshot: DataSnapshot) {

            }

            override fun onCancelled(error: DatabaseError) {

            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }
        })

    }

    fun sendMessage(messageText: String) {

        val messageId = chatRef.push().key ?: return
        val chatMessage = ChatMessage(
            id = messageId,
            senderId = "user_123",
            message = messageText,
            timestamp = System.currentTimeMillis()
        )
        chatRef.child(messageId).setValue(chatMessage)
    }
}