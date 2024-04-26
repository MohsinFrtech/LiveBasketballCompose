package com.example.livebasketballcompose.navigation

import com.example.livebasketballcompose.R
import com.example.livebasketballcompose.utils.AppConstants

sealed class BottomNavItem(val route: String, val icon: Int, val labelName: String) {
    object streaming :
        BottomNavItem(AppConstants.streaming, R.drawable.live_streaming, AppConstants.streaming)

    object score : BottomNavItem(AppConstants.score, R.drawable.live_scores, AppConstants.score)
    object league : BottomNavItem(AppConstants.league, R.drawable.leagues, AppConstants.league)
    object more : BottomNavItem(
        AppConstants.more, R.drawable.more, AppConstants.more
    )
    object channelScreen : BottomNavItem(
        AppConstants.channel, 0, AppConstants.channel
    )
    object notificationScreen : BottomNavItem(
        AppConstants.notification, 0, AppConstants.notification
    )
    object matchDescription : BottomNavItem(
        AppConstants.matchDescription, 0, AppConstants.matchDescription
    )
    object countryLeagues : BottomNavItem(
        AppConstants.countryLeagues, 0, AppConstants.countryLeagues
    )
}