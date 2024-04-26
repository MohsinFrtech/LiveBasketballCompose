package com.example.livebasketballcompose.networklayer.networkinterfaces


import com.example.livebasketballcompose.models.MainResponse
import com.example.livebasketballcompose.utils.AppConstants.Ip_Api_Base_Url
import com.example.livebasketballcompose.utils.AppConstants.streamingApiEndPoint
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface StreamingApiInterface {
    //streaming api interface
    @POST(streamingApiEndPoint)
    @Headers("Content-Type: application/json")
    fun getStreamingEvents(
        @Body body: RequestBody
    ): Call<MainResponse>

    @GET(Ip_Api_Base_Url)
    fun getIP(): Call<String?>?
}