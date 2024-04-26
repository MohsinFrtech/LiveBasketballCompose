package com.example.livebasketballcompose.models

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.RawValue

data class StandingsModel(

    @SerializedName("groups") var groups: ArrayList<Groups> = arrayListOf()

)

data class Groups(

    @SerializedName("name") var name: String? = null,
    @SerializedName("standings") var standings: ArrayList<Standings> = arrayListOf()

)

data class Standings(

    @SerializedName("position") var position: Int? = null,
    @SerializedName("stage") var stage: String? = null,
    @SerializedName("group") var group: Group? = Group(),
    @SerializedName("team") var team: Team? = Team(),
    @SerializedName("league") var league: LeaguePoints? = LeaguePoints(),
    @SerializedName("country") var country: CountryData? = CountryData(),
    @SerializedName("games") var games: Games? = Games(),
    @SerializedName("points") var points: Points? = Points(),
    @SerializedName("form") var form: String? = null,
    @SerializedName("description") var description: String? = null

)

data class CountryData(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("code") var code: String? = null,
    @SerializedName("flag") var flag: String? = null

)

data class Group(

    @SerializedName("name") var name: String? = null,
    @SerializedName("points") var points: Int? = null

)

data class Team(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("logo") var logo: String? = null

)

data class LeaguePoints(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("season") var season: @RawValue Any? = null,
    @SerializedName("logo") var logo: String? = null

)

data class Win(

    @SerializedName("total") var total: Int? = 0,
    @SerializedName("percentage") var percentage: String? = null

)

data class Lose(

    @SerializedName("total") var total: Int? = 0,
    @SerializedName("percentage") var percentage: String? = null

)

data class Games(

    @SerializedName("played") var played: Int? = 0,
    @SerializedName("win") var win: Win? = Win(),
    @SerializedName("lose") var lose: Lose? = Lose()

)

data class Points(

    @SerializedName("for") var forTeam: Int? = 0,
    @SerializedName("against") var against: Int? = null

)