package com.example.livebasketballcompose.networklayer.networkinterfaces

import com.example.livebasketballcompose.models.StandingsModel
import com.example.livebasketballcompose.models.countryLeagueModel
import com.example.livebasketballcompose.utils.AppConstants.countryLeague
import com.example.livebasketballcompose.utils.AppConstants.leagueStandings
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface ApiInterface {


    @POST(countryLeague)
    @Headers("Content-Type: application/json")
    fun getCountryLeague(
        @Body body: RequestBody
    ): Call<countryLeagueModel>


    @POST(leagueStandings)
    @Headers("Content-Type: application/json")
    fun getLeagueStandings(
        @Body body: RequestBody
    ): Call<StandingsModel>

}