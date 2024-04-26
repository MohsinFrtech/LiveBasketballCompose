package com.example.livebasketballcompose.networklayer.networkinterfaces

interface GeneralApiResponseListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}