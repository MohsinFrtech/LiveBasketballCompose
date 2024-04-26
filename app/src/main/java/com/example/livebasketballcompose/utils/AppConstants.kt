package com.example.livebasketballcompose.utils

import com.google.android.gms.ads.nativead.NativeAd

object AppConstants {
    const val streaming = "Streaming"
    const val score = "Score"
    const val league = "League"
    const val more = "More"
    const val channel = "Channel"
    const val notification = "Notification"
    const val matchDescription = "match description"
    const val countryLeagues = "country Leagues"
    const val countryLeague = "games"
    const val leagueStandings = "standings"
    var splash_status=false

    const val userRepAlgo = "[cCITS]"
    const val userBaseExtraDel2 = "%"
    const val typeFlussonic = "flussonic"
    const val userBaseDel = "/"
    const val userBase = "?token="
    const val algoTypeS1 = "SHA-1"
    const val algoTypeS2 = "SHA-256"
    const val algoName = "iso-8859-1"
    const val mySecretSize = 16
    var BASKET_API_BASE_URL = ""
    var Streaming_API_BASE_URL = ""
    const val Ip_Api_Base_Url = "https://ip-api.streamingucms.com/"
    const val streamingApiEndPoint = "details"
    var numberValues = ""
    var BASKETTOKEN = ""
    var passValue = ""
    var appTaken = ""
    var myUserLock1 = "locked"
    var myUserCheck1 = "myUserCheck1"
    var parsedString = ""
    var appId = "11"
    const val EXPANSTION_TRANSITION_DURATION = 450
    var app_update_dialog = false
    var networkIp = "userIp"
    var admobInterstitial = ""
    var facebookPlacementIdInterstitial = ""
    var fbPlacementIdBanner = ""
    var chartBoostAppID = ""
    var chartBoostAppSig = ""
    var nativeAdmob: String = ""
    var unityGameID = ""
    var startAppId = ""
    var nativeFacebook = ""
    var admobBannerId = ""
    var adLocation1Provider = ""
    //Ad Locations
    var location2TopProvider = "none"
    var location2BottomProvider = "none"
    var locationAfter = "none"
    var nativeAdProviderName="none"
    var locationBeforeProvider="none"
    var isInitAdmobSdk = false
    var isInitFacebookSdk = false
    var isUnitySdkInit = false
    var isChartboostSdkInit = false
    var isStartAppSdkInit = false
    var middleAdProvider = "none"
    //Ads
    const val unityTestMode = true
    const val admob = "admob"
    const val facebook = "facebook"
    const val chartBoost = "chartboost"
    const val unity = "unity"
    const val startApp = "startapp"
    //Ad Locations
    const val adMiddle = "Middle"
    const val adBefore = "BeforeVideo"
    const val adAfter = "AfterVideo"
    const val adLocation1 = "Location1"
    const val adLocation2top = "Location2Top"
    const val adLocation2bottom = "Location2Bottom"
    const val nativeAdLocation = "native"
     var selectedRoute=""

    const val mySecretCheckDel: String = "&"
    const val chName = "UTF-8"
    const val asp = "AES"
    const val instanceVal = "PBKDF2WithHmacSHA1"
    const val transForm = "AES/CBC/PKCS5Padding"
    const val preferenceKey="Message"
    const val adUnitId = "Interstitial_Android"
    const val EMAIL_SUPPORT = "android.developer@cricgenix.com"
    var currentNativeAd: NativeAd?=null
    var currentNativeAdFacebook:com.facebook.ads.NativeAd?=null

    //expanded recyclerview
    const val PARENT=0
    const val CHILD=1

    //expanded recyclerview
    const val STANDINGS_PARENT=0
    const val STANDINGS_CHILD=1
}