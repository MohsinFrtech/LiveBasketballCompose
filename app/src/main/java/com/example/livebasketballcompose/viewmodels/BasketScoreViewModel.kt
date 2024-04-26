package com.example.livebasketballcompose.viewmodels

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.livebasketballcompose.models.BasketData
import com.example.livebasketballcompose.models.Game
import com.example.livebasketballcompose.models.League
import com.example.livebasketballcompose.models.LeagueDetailRequest
import com.example.livebasketballcompose.models.StandingsModel
import com.example.livebasketballcompose.models.countryLeagueModel
import com.example.livebasketballcompose.networklayer.ApiController
import com.example.livebasketballcompose.networklayer.ApiState
import com.example.livebasketballcompose.networklayer.networkinterfaces.GeneralApiResponseListener
import com.example.livebasketballcompose.utils.AppConstants.BASKETTOKEN
import com.example.livebasketballcompose.utils.CodeUtils
import com.google.gson.Gson
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.await
import java.net.SocketTimeoutException
import java.net.UnknownHostException


class BasketScoreViewModel(application: Application) : AndroidViewModel(application) {

    private val app: Application = application
    private val tags: String = "ScoresViewModel"
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)
    var selectedGame:Game?=null
    var selectedLeague: League?=null


    var apiResponseListener: GeneralApiResponseListener? = null
    private var _isLoading = MutableStateFlow<ApiState>(ApiState.Empty)
    val isLoading: StateFlow<ApiState> get() = _isLoading

    private val _leagueStandingList = MutableStateFlow<StandingsModel?>(null)
    val leagueStandingList: StateFlow<StandingsModel?> get() = _leagueStandingList

    private val _countryLeagueList = MutableStateFlow<countryLeagueModel?>(null)
    val countryLeagueList: StateFlow<countryLeagueModel?> get() = _countryLeagueList
    private val _expandedCardIdsList = MutableStateFlow(listOf<Long>())
    val expandedCardIdsList: StateFlow<List<Long>> get() = _expandedCardIdsList
    private val _expandedCountryIdsList = MutableStateFlow(listOf<Long>())
    val expandedCountryIdsList: StateFlow<List<Long>> get() = _expandedCountryIdsList
    init {
        _countryLeagueList.value = null
        _leagueStandingList.value = null
//        getCountryWithLeague()
    }

    fun onRefreshSBasketScoresData() {
        getCountryWithLeague()
    }

    fun onCardArrowClicked(cardId: Long) {
        _expandedCardIdsList.value = _expandedCardIdsList.value.toMutableList().also { list ->
            if (list.contains(cardId)) list.remove(cardId) else list.add(cardId)
        }
    }

    fun onCountryArrowClicked(cardId: Long) {
        _expandedCountryIdsList.value = _expandedCountryIdsList.value.toMutableList().also { list ->
            if (list.contains(cardId)) list.remove(cardId) else list.add(cardId)
        }
    }
    private fun getCountryWithLeague() {
        _isLoading.value = ApiState.Loading
        if (CodeUtils.checkInternetIsAvailable(app)) {
            coroutineScope.launch {
                val basketData = BasketData()
                basketData.token = BASKETTOKEN
                val body = Gson().toJson(basketData)
                    .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
                val getResponse = ApiController.retrofitBuilderInstanceBasketApi.getCountryLeague(
                    body
                )
                try {
                    val responseResult = getResponse.await()
                    withContext(Dispatchers.Main) {
                        responseResult.let {
                            try {
                                _countryLeagueList.value = it
                                Log.d("basketApiError","msg")

                                _isLoading.value = ApiState.Success
                            } catch (e: Exception) {
                                apiResponseListener?.onFailure("Something is wrong with response")
                                _isLoading.value = ApiState.Error("Something is wrong with response")
                            }
                        }

                    }

                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        _isLoading.value = ApiState.Error("Something went wrong, Please try again")
                        apiResponseListener?.onFailure("Something went wrong, Please try again")
                    }
                    if (e is SocketTimeoutException) {
                        withContext(Dispatchers.Main) {
                            onRefreshSBasketScoresData()
                            _isLoading.value = ApiState.Error("Server is taking too long to respond.")
                            apiResponseListener?.onFailure("Server is taking too long to respond.")
                        }
                    }
                    if (e is UnknownHostException) {
                        withContext(Dispatchers.Main) {
                            onRefreshSBasketScoresData()
                            _isLoading.value = ApiState.Error("Server is taking too long to respond.")
                            apiResponseListener?.onFailure("Server is taking too long to respond.")
                        }
                    }
                }
            }


        } else {
            _isLoading.value = ApiState.Error("Something went wrong, Please try again")
        }

    }

    fun getLeagueDetails(id: Long, season: Any) {
        _isLoading.value = ApiState.Loading
        if (CodeUtils.checkInternetIsAvailable(app)) {
            coroutineScope.launch {
                val leagueDetails = LeagueDetailRequest()
                leagueDetails.token = BASKETTOKEN
                leagueDetails.league_id = id
                leagueDetails.season = season
                val body = Gson().toJson(leagueDetails)
                    .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
                val getResponse = ApiController.leagueDetailServiceApi.getLeagueStandings(
                    body
                )
                try {
                    val responseResult = getResponse.await()
                    withContext(Dispatchers.Main) {
                        responseResult.let {
                            try {
                                _leagueStandingList.value = it
                                _isLoading.value = ApiState.Success
                            } catch (e: Exception) {
                                _isLoading.value = ApiState.Error("Something is wrong with response")
                            }
                        }
//                        isLoading.value = false
                    }

                } catch (e: Exception) {
                    Log.d("Exception", "" + "coming34......" + e.localizedMessage)
                    withContext(Dispatchers.Main) {
                        _isLoading.value = ApiState.Error("Something went wrong, Please try again")
                    }
                }
            }


        } else {
            _isLoading.value = ApiState.Error("Something went wrong, Please try again")
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