package com.example.livebasketballcompose.networklayer

sealed class ApiState {
    object Empty : ApiState()
    object Loading : ApiState()
    data class Error(val message: String) : ApiState()
    object Success : ApiState()

}