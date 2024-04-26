package com.example.livebasketballcompose.viewmodels

import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.livebasketballcompose.BuildConfig
import com.example.livebasketballcompose.models.ApiParams
import com.example.livebasketballcompose.models.ApplicationConfiguration
import com.example.livebasketballcompose.models.Channel
import com.example.livebasketballcompose.models.DataModel
import com.example.livebasketballcompose.networklayer.ApiController
import com.example.livebasketballcompose.networklayer.ApiState
import com.example.livebasketballcompose.networklayer.networkinterfaces.GeneralApiResponseListener
import com.example.livebasketballcompose.utils.ApiResponse
import com.example.livebasketballcompose.utils.AppConstants
import com.example.livebasketballcompose.utils.AppConstants.Streaming_API_BASE_URL
import com.example.livebasketballcompose.utils.AppConstants.appId
import com.example.livebasketballcompose.utils.AppConstants.appTaken
import com.example.livebasketballcompose.utils.AppConstants.networkIp
import com.example.livebasketballcompose.utils.CodeUtils
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.await
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.ArrayList

class StreamingViewModel(app: Application) : AndroidViewModel(app) {
    private var viewModelJob = Job()
    private val appContext: Application = app
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)
    private var _isLoading = MutableStateFlow<ApiState>(ApiState.Empty)
    val isLoading: StateFlow<ApiState> get() = _isLoading
    var showSplashScreen by mutableStateOf(false)
    var showAppUpdateDialog by mutableStateOf(false)

    var splashText:String=""
    var buttonText:String=""
    var appUpdateText:String=""
    var isPermanent:Boolean=false


    var splashHeading:String=""
    var splashButtonLink=""
    private val streamingResponse = ApiResponse()
    var apiResponseListener: GeneralApiResponseListener? = null
    private var _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> get() = _isRefreshing.asStateFlow()

    private val _dataModelList = MutableLiveData<DataModel>()
    val dataModelList: LiveData<DataModel>
        get() = _dataModelList
    private val _dataModelListState2 = MutableStateFlow<DataModel?>(null)
    val dataModel2: StateFlow<DataModel?> get() = _dataModelListState2


    fun onRefreshEvents() {
        getLiveEventsFromRemote()
    }

    fun getLiveEventsFromRemote() {

        if (CodeUtils.checkInternetIsAvailable(appContext)) {
            _isLoading.value = ApiState.Loading
            if (Streaming_API_BASE_URL != "") {
                coroutineScope.launch {
                    val params = ApiParams()
                    params.id = appId
                    params.auth_token = appTaken
//                    params.build_no = BuildConfig.VERSION_CODE.toString()
                    params.build_no = "0"
                    val body = Gson().toJson(params)
                        .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
                    val getResponse = ApiController.apiServiceStreaming.getStreamingEvents(
                        body
                    )
                    try {

                        val responseResult = getResponse.await()
                        withContext(Dispatchers.Main) {
                            responseResult.let {
                                try {
                                    val model = streamingResponse.parseResponse(it?.data)
                                    checkSplashScreen(model)
                                    streamingResponse.getExtraValuesFromResponse(model)
                                    _isLoading.value = ApiState.Success
                                    _dataModelListState2.value = model
                                    _dataModelList.value = model
                                    _isRefreshing.emit(false)
                                    if (networkIp.equals("userIp", true)) {
                                        getIP()
                                    } else {

                                    }

                                } catch (e: Exception) {
                                    _isRefreshing.emit(false)

                                    apiResponseListener?.onFailure("Something is wrong with response")
                                    //e.printStackTrace();
                                }


                            }
                        }

                    } catch (e: Exception) {
                        Log.d("Exception", "" + "coming34......" + e.localizedMessage)
                        _isRefreshing.emit(false)

                        withContext(Dispatchers.Main) {
                            apiResponseListener?.onFailure("Something went wrong, Please try again")
                        }
                        if (e is SocketTimeoutException) {
                            withContext(Dispatchers.Main) {
                                apiResponseListener?.onFailure("Server is taking too long to respond.")
                            }
                        }
                        if (e is UnknownHostException) {
                            withContext(Dispatchers.Main) {
                                onRefreshEvents()
                                apiResponseListener?.onFailure("Server is taking too long to respond.")
                            }
                        }
                    }
                }

            } else {

                apiResponseListener?.onFailure("Server is taking too long to respond.")
            }

        } else {
            apiResponseListener?.onFailure("Internet connection lost! , please check your internet connection")
        }

    }

    private fun checkSplashScreen(model: DataModel) {
        if (!model?.application_configurations.isNullOrEmpty()) {
            var splashScreenStatus = false
             var time = "0"

            val refresh = Handler(Looper.getMainLooper())
            refresh.post {
                run {
                    for (configuration in model?.application_configurations!!) {

                        ///For setting time
                        if (configuration.key?.equals("Time", true)!!) {
                            if (configuration.value != null) {
                                time = configuration.value!!
                            }

                            if (splashScreenStatus) {
                                if (!AppConstants.splash_status) {
                                    AppConstants.splash_status = true
                                    showSplashScreen=true
                                    try {
                                        var timer: Int = time.toInt()
                                        timer *= 1000

                                        Handler(Looper.getMainLooper()).postDelayed({
                                            showSplashScreen=false

                                        }, timer.toLong())
                                    } catch (e: NumberFormatException) {

                                        Log.d("Exception", "" + e.message)

                                    }

                                }
                            }


                        }

                        ///For setting button text
                        if (configuration.key.equals("ButtonText", true)) {
                            if (configuration.value != null) {
                                buttonText= configuration.value!!
                            }

                        }

                        ///For setting heading
                        if (configuration.key.equals("Heading", true)) {
                            if (configuration.value != null) {
                               splashText= configuration.value!!
                            }

                        }

                        ///For setting link
                        if (configuration.key.equals("ButtonLink", true)) {
                            if (configuration.value != null) {

                            }

                        }

                        ///For setting body
                        if (configuration.key.equals("DetailText", true)) {
                            if (configuration.value != null) {
                                splashHeading=configuration.value!!
                            }

                        }

                        ///For setting show button
                        if (configuration.key.equals("ShowButton", true)) {
                            if (configuration.value != null) {
                                if (configuration.value.equals("True", true)) {
                                } else {

                                }

                            }

                        }

                        ///For checking splash is on and off
                        if (configuration.key.equals("ShowSplash", true)) {
                            if (configuration.value.equals("true", true)) {
                                if (!splashScreenStatus) {
                                    splashScreenStatus = true
                                }

                            } else {
                                splashScreenStatus = false
                            }

                        }


                    }////loop to iterate through configuration array
                }
            }

        }

        if (!model.app_version.isNullOrEmpty()) {
            try {
                val version = BuildConfig.VERSION_CODE
                try {
                    val parsedInt = model.app_version!!.toInt()
                    if (parsedInt > version) {
                        if (!AppConstants.app_update_dialog) {
                            showAppUpdateDialog=true
                            appUpdateText = model.app_update_text.toString()
                            isPermanent = model.is_permanent_dialog
                            AppConstants.app_update_dialog = true
                        }
                    }
                } catch (nfe: java.lang.NumberFormatException) {
                    showAppUpdateDialog=false
                }
            } catch (e: PackageManager.NameNotFoundException) {
                showAppUpdateDialog=false
            }
        }

    }

    private fun getIP() {
        if (CodeUtils.checkInternetIsAvailable(appContext)) {
            coroutineScope.launch {
                val getResponse = ApiController.apiServiceIPApi.getIP()
                try {
                    val responseResult = getResponse?.await()
                    withContext(Dispatchers.Main) {
                        if (responseResult != null) {
                            networkIp = responseResult.toString()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        }

    }

    // On ViewModel Cleared
    override fun onCleared() {
        super.onCleared()
        viewModelJob.let {
            viewModelJob.cancel()
        }

    }
}