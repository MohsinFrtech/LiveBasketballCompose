package com.example.livebasketballcompose.appads

import android.app.Activity
import android.content.Context
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.databinding.DataBindingUtil
import com.chartboost.sdk.Chartboost
import com.chartboost.sdk.ads.Interstitial
import com.chartboost.sdk.callbacks.InterstitialCallback
import com.chartboost.sdk.events.*
import com.chartboost.sdk.privacy.model.CCPA
import com.chartboost.sdk.privacy.model.COPPA
import com.chartboost.sdk.privacy.model.GDPR
import com.facebook.ads.*
import com.facebook.ads.AdError
import com.facebook.ads.AdSize
import com.google.android.gms.ads.*
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd
import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView
import com.startapp.sdk.ads.banner.Banner
import com.startapp.sdk.ads.banner.BannerListener
import com.startapp.sdk.adsbase.StartAppAd
import com.startapp.sdk.adsbase.StartAppSDK
import com.startapp.sdk.adsbase.adlisteners.AdDisplayListener
import com.startapp.sdk.adsbase.adlisteners.AdEventListener
import com.unity3d.ads.*
import com.unity3d.services.banners.BannerErrorInfo
import com.unity3d.services.banners.BannerView
import com.unity3d.services.banners.UnityBannerSize
import com.example.livebasketballcompose.R
import com.example.livebasketballcompose.appinterfaces.AdManagerListener
import com.example.livebasketballcompose.databinding.LayoutFbNativeAdBinding
import com.example.livebasketballcompose.models.AppAd
import com.example.livebasketballcompose.utils.AppConstants
import com.example.livebasketballcompose.utils.AppConstants.admobInterstitial
import com.example.livebasketballcompose.utils.Logger
import com.google.android.gms.ads.AdView

class AdManager(
    private val context: Context,
    private val activity: Activity,
    val adManagerListener: AdManagerListener
) {

    private val logger = Logger()
    private var adView: NativeAdView? = null
    private var nativeAdLayout: NativeAdLayout? = null
    private var chartboostInterstitial: Interstitial? = null
    private var fbNativeAd: NativeAd? = null
    private var facebookbinding: LayoutFbNativeAdBinding? = null
    private var bottomBanner: BannerView? = null
    private var bottomAdUnitId = "Banner_Android"
    private val taG = "AdManagerClass"
    private var bannerAdValue = ""
    private var interstitialAdValue = ""
    private var nativeAdValue = ""
    private var topBannerUnity: BannerView? = null

    private var startAppAd: StartAppAd? = null
    private var mInterstitialAd: AdManagerInterstitialAd? = null
    private var facebookinterstitial: InterstitialAd? = null
    private var currentNativeAd: com.google.android.gms.ads.nativead.NativeAd? = null
    private var adProvider = ""

    ///function will return provider
    fun checkProvider(list: List<AppAd>, location: String): String {
        adProvider = "none"
        for (listItem in list) {
            if (listItem.enable == true) {
                if (!listItem.ad_locations.isNullOrEmpty()) {
                    for (adLocation in listItem.ad_locations!!) {
                        if (adLocation.title.equals(location, true)) {
                            if (listItem.ad_provider.equals(AppConstants.admob, true)) {
                                adProvider = AppConstants.admob
                                checkAdValue(adLocation.title, listItem.ad_key, adProvider)
                            } else if (listItem.ad_provider.equals(AppConstants.facebook, true)) {
                                adProvider = AppConstants.facebook
                                checkAdValue(adLocation.title, listItem.ad_key, adProvider)
                            } else if (listItem.ad_provider.equals(AppConstants.unity, true)) {
                                adProvider = AppConstants.unity
                                checkAdValue(adLocation.title, listItem.ad_key, adProvider)
                            } else if (listItem.ad_provider.equals(AppConstants.chartBoost, true)) {
                                adProvider = AppConstants.chartBoost
                                checkAdValue(adLocation.title, listItem.ad_key, adProvider)
                            } else if (listItem.ad_provider.equals(AppConstants.startApp, true)) {
                                adProvider = AppConstants.startApp
                                checkAdValue(adLocation.title, listItem.ad_key, adProvider)
                            }

                        }


                    }

                }


            }

        }

        ////If provider exist then initialize sdk of the particular provider
        return adProvider
    }

    private fun checkAdValue(adLocation: String?, adKey: String?, provider: String) {

        if (adLocation.equals(AppConstants.adMiddle, true) || adLocation.equals(
                AppConstants.adBefore,
                true
            )
            || adLocation.equals(AppConstants.adAfter, true)
        ) {
            interstitialAdValue = adKey.toString()
            if (provider.equals(AppConstants.chartBoost, true)) {
                if (interstitialAdValue.contains(AppConstants.mySecretCheckDel)) {
                    val yourArray: List<String> =
                        interstitialAdValue.split(AppConstants.mySecretCheckDel)
                    AppConstants.chartBoostAppID = yourArray[0].trim()
                    AppConstants.chartBoostAppSig = yourArray[1].trim()
                }
            } else if (provider.equals(AppConstants.admob, true)) {
                AppConstants.admobInterstitial = interstitialAdValue
            } else if (provider.equals(AppConstants.facebook, true)) {
                AppConstants.facebookPlacementIdInterstitial = interstitialAdValue
            } else if (provider.equals(AppConstants.startApp, true)) {
                AppConstants.startAppId = interstitialAdValue
            } else if (provider.equals(AppConstants.unity, true)) {
                AppConstants.unityGameID = interstitialAdValue
            }

        } else if (adLocation.equals(AppConstants.nativeAdLocation, true)) {
            nativeAdValue = adKey.toString()

            if (provider.equals(AppConstants.admob, true)) {
                AppConstants.nativeAdmob = nativeAdValue

            } else if (provider.equals(AppConstants.facebook, true)) {
                AppConstants.nativeFacebook = nativeAdValue
            }

        } else if (adLocation.equals(AppConstants.adLocation1, true)
            || adLocation.equals(AppConstants.adLocation2top, true)
            || adLocation.equals(AppConstants.adLocation2bottom, true)
        ) {
            bannerAdValue = adKey.toString()
            if (provider.equals(AppConstants.admob, true)) {
                AppConstants.admobBannerId = bannerAdValue
            } else if (provider.equals(AppConstants.facebook, true)) {
                AppConstants.fbPlacementIdBanner = bannerAdValue
            } else if (provider.equals(AppConstants.unity, true)) {
                AppConstants.unityGameID = bannerAdValue

            } else if (provider.equals(AppConstants.startApp, true)) {
                AppConstants.startAppId = bannerAdValue

            }


        }
    }

    fun loadAdProvider(
        provider: String,
        adLocation: String,
        adView: LinearLayout?,
        linearLayout: LinearLayout?,
        relativeLayout: RelativeLayout?,
        startAppBanner: Banner?
    ) {

        if (provider.equals(AppConstants.admob, true)) {
            adMobSdkInitializationOrAdmobAd(
                adLocation,
                adView,
                linearLayout,
                relativeLayout,
                startAppBanner
            )
        } else if (provider.equals(AppConstants.facebook, true)) {
            facebookSdkInitialization(
                adLocation,
                adView,
                linearLayout,
                relativeLayout,
                startAppBanner
            )
        } else if (provider.equals(AppConstants.unity, true)) {
            unitySdkInitialization(adLocation, adView, linearLayout, relativeLayout, startAppBanner)
        } else if (provider.equals(AppConstants.chartBoost, true)) {
            chartboostSdkInitialization(
                adLocation,
                adView,
                linearLayout,
                relativeLayout,
                startAppBanner
            )
        } else if (provider.equals(AppConstants.startApp, true)) {
            startAppSdkInitialization(
                adLocation,
                adView,
                linearLayout,
                relativeLayout,
                startAppBanner
            )
        }
    }


    private fun unitySdkInitialization(
        adLocation: String,
        adView: LinearLayout?,
        linearLayout: LinearLayout?,
        relativeLayout: RelativeLayout?,
        banner: Banner?
    ) {

        if (AppConstants.isUnitySdkInit) {
            loadAdAtParticularLocation(
                adLocation,
                AppConstants.unity, adView, linearLayout, relativeLayout, banner
            )
        } else {
            UnityAds.initialize(
                context,
                AppConstants.unityGameID,
                AppConstants.unityTestMode,
                object : IUnityAdsInitializationListener {
                    override fun onInitializationComplete() {
                        if (UnityAds.isInitialized()) {

                            AppConstants.isUnitySdkInit = true
                            loadAdAtParticularLocation(
                                adLocation,
                                AppConstants.unity, adView, linearLayout, relativeLayout, banner
                            )

                        }

                    }

                    override fun onInitializationFailed(
                        p0: UnityAds.UnityAdsInitializationError?,
                        p1: String?
                    ) {

                        AppConstants.isUnitySdkInit = false

                    }
                })

        }
    }


    private fun setUpUnityBannerBottom(relativeLayout: RelativeLayout?) {

        relativeLayout?.removeAllViews()
        bottomBanner = BannerView(activity, bottomAdUnitId, UnityBannerSize(320, 50))
        val bannerListener: BannerView.IListener = object : BannerView.IListener {
            override fun onBannerLoaded(bannerAdView: BannerView) {
                // Called when the banner is loaded.
                logger.printLog(taG, "unityLoaded")

                if (bottomBanner != null) {
                    showBanner(relativeLayout, bottomBanner!!)
                }

            }

            override fun onBannerFailedToLoad(
                bannerAdView: BannerView,
                errorInfo: BannerErrorInfo
            ) {
                logger.printLog(taG, "unityFailed" + "  " + errorInfo.errorMessage)
            }

            override fun onBannerClick(bannerAdView: BannerView) {

            }

            override fun onBannerLeftApplication(bannerAdView: BannerView) {
            }
        }
        bottomBanner?.listener = bannerListener
        bottomBanner?.load()
    }


    private fun setUpUnityBanner(relativeLayout: RelativeLayout?) {
        // Listener for banner events:

        relativeLayout?.removeAllViews()

        topBannerUnity = BannerView(activity, bottomAdUnitId, UnityBannerSize(320, 50))
        val bannerListener: BannerView.IListener = object : BannerView.IListener {
            override fun onBannerLoaded(bannerAdView: BannerView) {
                // Called when the banner is loaded.
                logger.printLog(taG, "unityLoaded")

                if (topBannerUnity != null) {
                    showBanner(relativeLayout, topBannerUnity!!)
                }

            }

            override fun onBannerFailedToLoad(
                bannerAdView: BannerView,
                errorInfo: BannerErrorInfo
            ) {
                logger.printLog(taG, "unityFailed" + "  " + errorInfo.errorMessage)
            }

            override fun onBannerClick(bannerAdView: BannerView) {

            }

            override fun onBannerLeftApplication(bannerAdView: BannerView) {
            }
        }
        topBannerUnity?.listener = bannerListener
        topBannerUnity?.load()

    }

    private fun showBanner(s: RelativeLayout?, insideBanner: BannerView) {
//        s?.removeAllViews()
        s?.addView(insideBanner)
    }


    ///Function to initialize admob sdk...
    private fun adMobSdkInitializationOrAdmobAd(
        locationName: String,
        adView: LinearLayout?,
        linearLayout: LinearLayout?,
        relativeLayout: RelativeLayout?,
        banner: Banner?
    ) {
        if (AppConstants.isInitAdmobSdk) {
            loadAdAtParticularLocation(
                locationName,
                AppConstants.admob, adView, linearLayout, relativeLayout, banner
            )
        } else {
            MobileAds.initialize(context) { p0 ->
                AppConstants.isInitAdmobSdk = true
                loadAdAtParticularLocation(
                    locationName,
                    AppConstants.admob, adView, linearLayout, relativeLayout, banner
                )
            }
        }


    }


    fun showAds(adProviderShow: String) {
        if (adProviderShow.equals(AppConstants.admob, true)) {
            showAdmobInterstitial()
        } else if (adProviderShow.equals(AppConstants.unity, true)) {
            showUnityAd()
        } else if (adProviderShow.equals(AppConstants.chartBoost, true)) {
            showChartBoost()
        } else if (adProviderShow.equals(AppConstants.facebook, true)) {
            showFacebookAdInterstitial()
        } else if (adProviderShow.equals(AppConstants.startApp, true)) {

            showStartAppAd()
        }

    }


    private fun showUnityAd() {
        val showListener: IUnityAdsShowListener = object : IUnityAdsShowListener {
            override fun onUnityAdsShowFailure(
                placementId: String,
                error: UnityAds.UnityAdsShowError,
                message: String
            ) {
                adManagerListener.onAdFinish()

            }

            override fun onUnityAdsShowStart(placementId: String) {

            }

            override fun onUnityAdsShowClick(placementId: String) {

            }

            override fun onUnityAdsShowComplete(
                placementId: String,
                state: UnityAds.UnityAdsShowCompletionState
            ) {
                adManagerListener.onAdFinish()

            }
        }
        UnityAds.show(
            activity,
            "Interstitial_Android",
            UnityAdsShowOptions(),
            showListener
        )

    }

    private fun loadAdAtParticularLocation(
        locationName: String,
        adProviderName: String,
        adView: LinearLayout?,
        linearLayout: LinearLayout?,
        relativeLayout: RelativeLayout?,
        banner: Banner?
    ) {


        if (locationName.equals(AppConstants.adLocation1, true) ||
            locationName.equals(AppConstants.adLocation2top, true) ||
            locationName.equals(AppConstants.adLocation2bottom, true)
        ) {

            if (adProviderName.equals(AppConstants.admob, true)) {
                loadAdmobBanner(adView)
            } else if (adProviderName.equals(AppConstants.facebook, true)) {
                loadFaceBookBannerAd(context, linearLayout)
            } else if (adProviderName.equals(AppConstants.unity, true)) {

                if (locationName.equals(AppConstants.adLocation1, true)) {
                    setUpUnityBanner(relativeLayout)
                }
                if (locationName.equals(AppConstants.adLocation2top, true)) {
                    setUpUnityBanner(relativeLayout)
                }

                if (locationName.equals(AppConstants.adLocation2bottom, true)) {
                    setUpUnityBannerBottom(relativeLayout)
                }
            } else if (adProviderName.equals(AppConstants.startApp, true)) {
                setStartAppBanner(banner)
            }


        } else if (locationName.equals(AppConstants.nativeAdLocation)) {
            if (adProviderName.equals(AppConstants.admob, true)) {
                loadAdmobNativeAdWithoutPopulate()
            } else if (adProviderName.equals(AppConstants.facebook, true)) {
                loadFacebookNativeAdWithoutPopulate()
            }
        } else {

            if (adProviderName.equals(AppConstants.admob, true)) {
                loadAdmobInterstitialAd()
            } else if (adProviderName.equals(AppConstants.unity, true)) {
                loadUnityAdInterstitial()
            } else if (adProviderName.equals(AppConstants.chartBoost, true)) {
                loadChartBoost()
            } else if (adProviderName.equals(AppConstants.facebook, true)) {
                loadFacebookInterstitialAd()
            } else if (adProviderName.equals(AppConstants.startApp, true)) {
                loadStartAppAd()
            }

        }

    }


    private fun loadStartAppAd() {
        startAppAd = StartAppAd(context)
        startAppAd?.loadAd(object : AdEventListener {

            override fun onReceiveAd(p0: com.startapp.sdk.adsbase.Ad) {

                showStartAppAd()

            }

            override fun onFailedToReceiveAd(p0: com.startapp.sdk.adsbase.Ad?) {

            }
        })
    }


    private fun showStartAppAd() {
        startAppAd?.showAd(object : AdDisplayListener {
            override fun adHidden(ad: com.startapp.sdk.adsbase.Ad) {


            }

            override fun adDisplayed(ad: com.startapp.sdk.adsbase.Ad) {


            }

            override fun adClicked(ad: com.startapp.sdk.adsbase.Ad) {

            }

            override fun adNotDisplayed(ad: com.startapp.sdk.adsbase.Ad) {
            }
        })
    }

    private fun loadUnityAdInterstitial() {
        val loadListener: IUnityAdsLoadListener = object : IUnityAdsLoadListener {
            override fun onUnityAdsAdLoaded(placementId: String) {
                adManagerListener.onAdLoad("success")

            }

            override fun onUnityAdsFailedToLoad(
                placementId: String,
                error: UnityAds.UnityAdsLoadError,
                message: String
            ) {

                adManagerListener.onAdLoad("failed")


            }
        }


        UnityAds.load("Interstitial_Android", loadListener)
    }


    private fun chartboostSdkInitialization(
        adLocation: String,
        adView: LinearLayout?,
        linearLayout: LinearLayout?,
        relativeLayout: RelativeLayout?,
        banner: Banner?
    ) {
        if (AppConstants.isChartboostSdkInit) {
            loadAdAtParticularLocation(
                adLocation,
                AppConstants.chartBoost, adView, linearLayout, relativeLayout, banner
            )

        } else {
            Chartboost.addDataUseConsent(context, GDPR(GDPR.GDPR_CONSENT.BEHAVIORAL))
            Chartboost.addDataUseConsent(context, CCPA(CCPA.CCPA_CONSENT.OPT_IN_SALE))
            Chartboost.addDataUseConsent(context, COPPA(true))

            Chartboost.startWithAppId(
                context, AppConstants.chartBoostAppID, AppConstants.chartBoostAppSig
            ) { startError: StartError? ->
                if (startError == null) {
                    AppConstants.isChartboostSdkInit = true
                    loadAdAtParticularLocation(
                        adLocation,
                        AppConstants.chartBoost, adView, linearLayout, relativeLayout, banner
                    )

                    // handle success
                } else {

                    // handle failure
                }
            }
        }


    }


    private fun facebookSdkInitialization(
        adLocation: String,
        adView: LinearLayout?,
        linearLayout: LinearLayout?,
        relativeLayout: RelativeLayout?,
        banner: Banner?
    ) {
        if (AppConstants.isInitFacebookSdk) {
            loadAdAtParticularLocation(
                adLocation,
                AppConstants.facebook, adView, linearLayout, relativeLayout, banner
            )
        } else {
            AudienceNetworkAds
                .buildInitSettings(context)
                .withInitListener {
                    if (it.isSuccess) {
                        AppConstants.isInitFacebookSdk = true
                        loadAdAtParticularLocation(
                            adLocation,
                            AppConstants.facebook, adView, linearLayout, relativeLayout, banner
                        )

                    } else {

                        AppConstants.isInitFacebookSdk = false
                    }

                }
                .initialize()


        }


    }


    ////startapp sdk initialization....
    private fun startAppSdkInitialization(
        adLocation: String,
        adView: LinearLayout?,
        linearLayout: LinearLayout?,
        relativeLayout: RelativeLayout?,
        banner: Banner?
    ) {

        if (AppConstants.isStartAppSdkInit) {
            loadAdAtParticularLocation(
                adLocation,
                AppConstants.startApp, adView, linearLayout, relativeLayout, banner
            )
        } else {
            try {
                StartAppSDK.init(context, AppConstants.startAppId, false)
                StartAppAd.disableSplash()
//                StartAppSDK.setTestAdsEnabled(true)
                AppConstants.isStartAppSdkInit = true
                loadAdAtParticularLocation(
                    adLocation,
                    AppConstants.startApp, adView, linearLayout, relativeLayout, banner
                )


            } catch (e: Exception) {

                logger.printLog(taG, "StartAppError" + e.message)
            }
        }


    }


    private fun setStartAppBanner(bannerView: Banner?) {
        val banner = Banner(activity, object : BannerListener {
            override fun onReceiveAd(p0: View?) {

                bannerView?.visibility = View.VISIBLE
                bannerView?.showBanner()
            }

            override fun onFailedToReceiveAd(p0: View?) {
                bannerView?.visibility = View.GONE

            }

            override fun onImpression(p0: View?) {

            }

            override fun onClick(p0: View?) {

            }


        })
        banner.loadAd()
    }


    //loadChartBoost
    private fun loadChartBoost() {
        chartboostInterstitial = Interstitial("location", object : InterstitialCallback {
            override fun onAdDismiss(event: DismissEvent) {
                adManagerListener.onAdFinish()
            }

            override fun onAdLoaded(event: CacheEvent, error: CacheError?) {
                adManagerListener.onAdLoad("success")
            }

            override fun onAdRequestedToShow(event: ShowEvent) {}
            override fun onAdShown(event: ShowEvent, error: ShowError?) {


            }

            override fun onAdClicked(event: ClickEvent, error: ClickError?) {
                adManagerListener.onAdFinish()
            }

            override fun onImpressionRecorded(event: ImpressionEvent) {}


        })
        chartboostInterstitial?.cache()
    }

    ///Show chartboost ads
    private fun showChartBoost(
    ) {

        if (chartboostInterstitial?.isCached() == true) { // check is cached is not mandatory
            chartboostInterstitial?.show()
        } else {
            adManagerListener.onAdFinish()
        }

    }

    ///setAdmobBanner....
     fun loadAdmobBanner(adViewLayout: LinearLayout?) {

        adViewLayout?.removeAllViews()

        val adView = AdView(context)
        adView.setAdSize(com.google.android.gms.ads.AdSize.BANNER)
        adView.adUnitId = AppConstants.admobBannerId
        adViewLayout?.addView(adView)
        adView.adListener = object : AdListener() {
            override fun onAdClicked() {

            }

            override fun onAdFailedToLoad(p0: LoadAdError) {
                Log.d(
                    "BannerLoad", "load" + AppConstants.admobBannerId + "  " + p0.code
                )
                adViewLayout?.visibility = View.GONE

            }

            override fun onAdLoaded() {
                Log.d("BannerLoad", "load")

                adViewLayout?.visibility = View.VISIBLE

            }

            override fun onAdClosed() {

            }
        }

        val adRequest = AdRequest.Builder()
            .build()
        adView.loadAd(adRequest)
    }

    ///LoadFacebook banner ad.....
    private fun loadFaceBookBannerAd(context: Context?, adContainer: LinearLayout?) {
        val adView =
            com.facebook.ads.AdView(context, AppConstants.fbPlacementIdBanner, AdSize.BANNER_HEIGHT_50)
        // AdSettings.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
        // Add the ad view to your activity layout
        adContainer?.removeAllViews()
        adContainer?.addView(adView)

        val adListener: com.facebook.ads.AdListener = object : com.facebook.ads.AdListener {
            override fun onError(ad: Ad?, adError: AdError) {
                // Ad error callback

            }

            override fun onAdLoaded(ad: Ad?) {
                // Ad loaded callback

                adContainer?.visibility = View.VISIBLE
            }

            override fun onAdClicked(ad: Ad?) {
                // Ad clicked callback
            }

            override fun onLoggingImpression(ad: Ad?) {
                // Ad impression logged callback
            }
        }
        // Request an ad
        adView.loadAd(adView.buildLoadAdConfig().withAdListener(adListener).build())


    }


    ///Admob load function...
    private fun loadAdmobInterstitialAd() {

        val adRequest = AdManagerAdRequest.Builder().build()

        AdManagerInterstitialAd.load(context, admobInterstitial,
            adRequest, object : AdManagerInterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    logger.printLog(taG, "admobProvider : ${adError.message}")
                    mInterstitialAd = null
                    adManagerListener.onAdLoad("failed")
                }

                override fun onAdLoaded(interstitialAd: AdManagerInterstitialAd) {
                    logger.printLog(taG, "admobProvider : ${interstitialAd.responseInfo}")
                    mInterstitialAd = interstitialAd
                    adManagerListener.onAdLoad("success")
                }
            })

    }


    ////showAdmobInterstitial
    private fun showAdmobInterstitial() {

        mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdClicked() {
                // Called when a click is recorded for an ad.
            }

            override fun onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                mInterstitialAd = null
                adManagerListener.onAdFinish()
            }

            override fun onAdFailedToShowFullScreenContent(p0: com.google.android.gms.ads.AdError) {
                // Called when ad fails to show.
                mInterstitialAd = null
                adManagerListener.onAdFinish()
            }

            override fun onAdImpression() {
                // Called when an impression is recorded for an ad.
            }

            override fun onAdShowedFullScreenContent() {
                // Called when ad is shown.
            }
        }



        if (mInterstitialAd != null) {
            mInterstitialAd?.show(activity)
        } else {
            adManagerListener.onAdFinish()
            logger.printLog(taG, "admob interstitial not loaded successfully")
        }


    }

    fun loadFacebookNativeAdWithoutPopulate() {
        fbNativeAd = NativeAd(context, AppConstants.nativeFacebook)
        val nativeAdListener: NativeAdListener = object : NativeAdListener {
            override fun onMediaDownloaded(ad: Ad) {

                // Native ad finished downloading all assets
            }

            override fun onError(ad: Ad?, adError: AdError) {


                // Native ad failed to load
            }

            override fun onAdLoaded(ad: Ad) {
                // Native ad is loaded and ready to be displayed
                if (fbNativeAd != null) {
//                    AppConstants.currentNativeAdFacebook = fbNativeAd
//                    inflateFbNativeAd(fbNativeAd!!, nativeAdLayout)

                }
            }

            override fun onAdClicked(ad: Ad) {
                // Native ad clicked
            }

            override fun onLoggingImpression(ad: Ad) {
                // Native ad impression
            }
        }

        // Request an ad
        fbNativeAd?.buildLoadAdConfig()
            ?.withAdListener(nativeAdListener)
            ?.build().let {
                fbNativeAd?.loadAd(
                    it
                )
            }
    }


    fun loadFacebookNativeAd(nativeAdLayout: NativeAdLayout) {
        fbNativeAd = NativeAd(context, AppConstants.nativeFacebook)
        val nativeAdListener: NativeAdListener = object : NativeAdListener {
            override fun onMediaDownloaded(ad: Ad) {

                // Native ad finished downloading all assets
            }

            override fun onError(ad: Ad?, adError: AdError) {


                // Native ad failed to load
            }

            override fun onAdLoaded(ad: Ad) {
                // Native ad is loaded and ready to be displayed
                if (fbNativeAd != null) {
                    inflateFbNativeAd(fbNativeAd!!, nativeAdLayout)

                }
            }

            override fun onAdClicked(ad: Ad) {
                // Native ad clicked
            }

            override fun onLoggingImpression(ad: Ad) {
                // Native ad impression
            }
        }

        // Request an ad
        fbNativeAd?.buildLoadAdConfig()
            ?.withAdListener(nativeAdListener)
            ?.build().let {
                fbNativeAd?.loadAd(
                    it
                )
            }
    }


    //    ////To inflate facebook native view
    fun inflateFbNativeAd(
        fbNativeAd: NativeAd, nativeAdLayout2: NativeAdLayout
    ) {
        fbNativeAd.unregisterView()
        nativeAdLayout = nativeAdLayout2
        val inflater = LayoutInflater.from(context)
        val fbAdView =
            inflater.inflate(
                R.layout.layout_fb_native_ad,
                nativeAdLayout,
                false
            ) as LinearLayout?
        nativeAdLayout?.addView(fbAdView)
        facebookbinding = fbAdView?.let { DataBindingUtil.bind(it) }
        // Add the AdOptionsView
        val adOptionsView = AdOptionsView(context, fbNativeAd, nativeAdLayout)
        facebookbinding?.adChoicesContainer?.removeAllViews()
        facebookbinding?.adChoicesContainer?.addView(adOptionsView, 0)
        // Set the Text.
        facebookbinding?.nativeAdTitle?.text = fbNativeAd.advertiserName
        facebookbinding?.nativeAdBody?.text = fbNativeAd.adBodyText
        facebookbinding?.nativeAdSocialContext?.text = fbNativeAd.adSocialContext
        if (fbNativeAd.hasCallToAction()) {
            facebookbinding?.nativeAdCallToAction?.visibility = View.VISIBLE
        } else {
            facebookbinding?.nativeAdCallToAction?.visibility = View.INVISIBLE
        }
        facebookbinding?.nativeAdCallToAction?.text = fbNativeAd.adCallToAction
        facebookbinding?.nativeAdSponsoredLabel?.text = fbNativeAd.sponsoredTranslation

        val clickableViews = ArrayList<View>()
        facebookbinding?.nativeAdTitle?.let { clickableViews.add(it) }
        facebookbinding?.nativeAdCallToAction?.let { clickableViews.add(it) }


        fbNativeAd.registerViewForInteraction(
            fbAdView,
            facebookbinding?.nativeAdMedia,
            facebookbinding?.nativeAdIcon,
            clickableViews
        )
    }


//    fun populateNativeAdView(
//        nativeAd: com.google.android.gms.ads.nativead.NativeAd,
//        adView: NativeAdView
//    ) {
//        try {
//            adView.visibility = View.VISIBLE
//            // Set the media view.
////            adView.mediaView = adView.findViewById(com.google.android.gms.ads.R.id.ad_media)
//            // Set other ad assets.
//            adView.headlineView = adView.findViewById(R.id.headline)
////            adView.bodyView = adView.findViewById(com.google.android.ads.nativetemplates.R.id.body)
//            adView.callToActionView = adView.findViewById(R.id.cta)
//            adView.iconView = adView.findViewById(R.id.icon)
////            adView.priceView = adView.findViewById(com.google.android.gms.ads.R.id.ad_price)
//            adView.starRatingView = adView.findViewById(R.id.rating_bar)
//            adView.storeView = adView.findViewById(R.id.secondary)
////            adView.advertiserView = adView.findViewById(com.google.android.gms.ads.R.id.ad_advertiser)
//
//            // The headline and media content are guaranteed to be in every UnifiedNativeAd.
////            (adView.headlineView as TextView).text = nativeAd.headline
////            nativeAd.mediaContent?.let { adView.mediaView?.setMediaContent(it) }
//
//            // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
//            // check before trying to display them.
////            if (nativeAd.body == null) {
////                adView.bodyView?.visibility = View.INVISIBLE
////            } else {
////                adView.bodyView?.visibility = View.VISIBLE
////                (adView.bodyView as TextView).text = nativeAd.body
////            }
//
//            if (nativeAd.callToAction == null) {
//                adView.callToActionView?.visibility = View.INVISIBLE
//            } else {
//                adView.callToActionView?.visibility = View.VISIBLE
//                (adView.callToActionView as Button).text = nativeAd.callToAction
//            }
//
//
//            if (nativeAd.icon == null) {
//                adView.iconView?.visibility = View.GONE
//            } else {
//                (adView.iconView as ImageView).setImageDrawable(
//                    nativeAd.icon?.drawable
//                )
//                adView.iconView?.visibility = View.VISIBLE
//            }
//
////            if (nativeAd.price == null) {
////                adView.priceView?.visibility = View.INVISIBLE
////            } else {
////                adView.priceView?.visibility = View.VISIBLE
////                (adView.priceView as TextView).text = nativeAd.price
////            }
//
//            if (nativeAd.store == null) {
//                adView.storeView?.visibility = View.INVISIBLE
//            } else {
//                adView.storeView?.visibility = View.VISIBLE
//                (adView.storeView as TextView).text = nativeAd.store
//            }
//
//            if (nativeAd.starRating == null) {
//                adView.starRatingView?.visibility = View.INVISIBLE
//            } else {
//
//                (adView.starRatingView as RatingBar).rating = nativeAd.starRating!!.toFloat()
//                adView.starRatingView?.visibility = View.VISIBLE
//            }
//
//            if (nativeAd.advertiser == null) {
//                adView.advertiserView?.visibility = View.INVISIBLE
//            } else {
//                (adView.advertiserView as TextView).text = nativeAd.advertiser
//                adView.advertiserView?.visibility = View.VISIBLE
//            }
//
//            // This method tells the Google Mobile Ads SDK that you have finished populating your
//            // native ad view with this native ad.
//            adView.setNativeAd(nativeAd)
//
//
//            // Get the video controller for the ad. One will always be provided, even if the ad doesn't
////            // have a video asset.
////            val vc = nativeAd.mediaContent?.videoController
////
////            // Updates the UI to say whether or not this ad has a video asset.
////            if (vc?.hasVideoContent() == true) {
////                // Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
////                // VideoController will call methods on this object when events occur in the video
////                // lifecycle.
////                vc.videoLifecycleCallbacks = object : VideoController.VideoLifecycleCallbacks() {
////
////                }
////            }
//        } catch (e: Exception) {
//
//        }
//
//    }


    ///load facebook interstitial....
    private fun loadFacebookInterstitialAd() {
        facebookinterstitial =
            InterstitialAd(context, AppConstants.facebookPlacementIdInterstitial)
        val adListener = object : InterstitialAdListener {

            override fun onInterstitialDisplayed(p0: Ad?) {

            }

            override fun onAdClicked(p0: Ad?) {
                adManagerListener.onAdFinish()
            }

            override fun onInterstitialDismissed(p0: Ad?) {
                adManagerListener.onAdFinish()
            }

            override fun onError(p0: Ad?, p1: AdError?) {
                adManagerListener.onAdLoad("failed")
            }

            override fun onAdLoaded(p0: Ad?) {
                adManagerListener.onAdLoad("success")
            }

            override fun onLoggingImpression(p0: Ad?) {

            }


        }
        val loadAdConfig = facebookinterstitial!!.buildLoadAdConfig()
            .withAdListener(adListener)
            .build()

        facebookinterstitial!!.loadAd(loadAdConfig)

    }

    private fun loadAdmobNativeAdWithoutPopulate() {
        val builder = AdLoader.Builder(context, AppConstants.nativeAdmob)
        builder.forNativeAd { nativeAd ->

            AppConstants.currentNativeAd?.destroy()
            AppConstants.currentNativeAd = nativeAd
        }

        val videoOptions = VideoOptions.Builder()
            .setStartMuted(true)
            .build()

        val adOptions = NativeAdOptions.Builder()
            .setVideoOptions(videoOptions)
            .build()

        builder.withNativeAdOptions(adOptions)

        val adLoader = builder.withAdListener(object : AdListener() {
            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                Log.d("adfailed", "fail" + loadAdError)
            }

            override fun onAdLoaded() {
                Log.d("adfailed", "loaded")

            }
        }).build()

        adLoader.loadAd(AdRequest.Builder().build())

    }

    /// show facebook interstitial....
    private fun showFacebookAdInterstitial() {
        if (facebookinterstitial != null) {
            if (facebookinterstitial!!.isAdLoaded) {

                try {
                    facebookinterstitial!!.show()
                } catch (e: Throwable) {
                    adManagerListener.onAdFinish()
                    logger.printLog(taG, "Exception" + e.message)
                }


            } else {

                adManagerListener.onAdFinish()
            }
        } else {

            adManagerListener.onAdFinish()
        }


    }


}