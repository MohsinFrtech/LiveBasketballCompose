package com.example.livebasketballcompose.models

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.lang.reflect.Type

@Parcelize
@JsonClass(generateAdapter = true)
data class countryLeagueModel(
    val countries: List<Country>? = null
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Country(
    val id: Long?=0,
    val name: String?,
    val code: String?,
    val flag: String?,
    val leagues: List<League>? = null
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class League(
    val id: Long?,
    val name: String?,
    val type: String?,
    val season: @RawValue Any,
    val games: List<Game>? = null,
    val logo: String? = null
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Game(
    val id: Long?,
    val date: String?,
    val time: String?,
    val timestamp: Long?,
    val timezone: String?,
    val week: String? = null,
    val status: Status?,
    val league: League?,
    val country: Country?,
    val teams: Teams?,
    val scores: Scores?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Status(
    @Json(name = "long")
    val longName: String? = "",
    val short: String?,
    val timer: String? = null
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Teams(
    val home: TeamsAway?,
    val away: TeamsAway?
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class TeamsAway(
    val id: Long,
    val name: String,
    val logo: String
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class Scores(
    val home: ScoresAway,
    val away: ScoresAway
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class ScoresAway(
    @Json(name = "quarter_1")
    val quarter1: Long? = 0,

    @Json(name = "quarter_2")
    val quarter2: Long? = 0,

    @Json(name = "quarter_3")
    val quarter3: Long? = 0,

    @Json(name = "quarter_4")
    val quarter4: Long? = 0,

    @Json(name = "over_time")
    val overTime: Long? = 0,

    val total: Long? = null
) : Parcelable