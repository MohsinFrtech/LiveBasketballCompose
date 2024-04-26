package com.example.livebasketballcompose.networklayer


import com.example.livebasketballcompose.BuildConfig
import com.example.livebasketballcompose.networklayer.networkinterfaces.ApiInterface
import com.example.livebasketballcompose.networklayer.networkinterfaces.StreamingApiInterface
import com.example.livebasketballcompose.utils.AppConstants.BASKET_API_BASE_URL
import com.example.livebasketballcompose.utils.AppConstants.Ip_Api_Base_Url
import com.example.livebasketballcompose.utils.AppConstants.Streaming_API_BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ApiController {
    private val moshiConverter = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    ////Gson converter....
    private val gsonConverter: Gson = GsonBuilder()
        .setLenient()
        .create()

    private val okHttpClient: OkHttpClient by lazy {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        OkHttpClient.Builder()
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .connectTimeout(90, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }


    private val retrofitInstance: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASKET_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshiConverter))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())

    }
    private val retrofitInstanceLeague: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASKET_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gsonConverter))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())

    }
    private val retrofitInstanceStreaming: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(Streaming_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshiConverter))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())

    }
    private val retrofitInstanceIpApi: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(Ip_Api_Base_Url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gsonConverter))
    }
    val retrofitBuilderInstanceBasketApi: ApiInterface by lazy {
        retrofitInstance
            .build()
            .create(ApiInterface::class.java)
    }

    val apiServiceStreaming: StreamingApiInterface by lazy {
        retrofitInstanceStreaming
            .build()
            .create(StreamingApiInterface::class.java)
    }

    val apiServiceIPApi: StreamingApiInterface by lazy {
        retrofitInstanceIpApi
            .build()
            .create(StreamingApiInterface::class.java)
    }
    val leagueDetailServiceApi: ApiInterface by lazy {
        retrofitInstanceLeague
            .build()
            .create(ApiInterface::class.java)
    }
}