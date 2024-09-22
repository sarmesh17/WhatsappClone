package com.sharkdroid.whatsappclone.models

data class ChatMessage(
    val id:String="",
    val senderId:String="",
    val message:String="",
    val timestamp:Long = 0L
)
