package com.example.livebasketballcompose.appads

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.example.livebasketballcompose.MyApp
import java.util.*
////AppOpen manager class for handling app open ads
class AppOpenManager(myApplication: MyApp?) : Application.ActivityLifecycleCallbacks,
    DefaultLifecycleObserver {

    private var myApplication: MyApp? = null
    private var appOpenAdManager: AppOpenAdManager

    private var currentActivity: Activity? = null

    init {
        this.myApplication = myApplication
        this.myApplication?.registerActivityLifecycleCallbacks(this)
        appOpenAdManager = AppOpenAdManager()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    override fun onResume(owner: LifecycleOwner) {
        onMoveToForeground()
    }

    private fun onMoveToForeground() {

        // Show the ad (if available) when the app moves to foreground.
        if (!currentActivity?.localClassName.equals("ui.activities.SplashScreen",true))
        {


        }
        currentActivity?.let { appOpenAdManager.showAdIfAvailable(it) }
    }

    interface OnShowAdCompleteListener {
        fun onShowAdComplete()
    }

    private inner class AppOpenAdManager {

        private var appOpenAd: AppOpenAd? = null
        private var isLoadingAd = false
        var isShowingAd = false
        private var loadTime: Long = 0


        fun loadAd(context: Context) {
            // Do not load ad if there is an unused ad or one is already loading.
            if (isLoadingAd || isAdAvailable()) {
                return
            }

//            ca-app-pub-3634409289872167/1657403501
            isLoadingAd = true
            val request = AdRequest.Builder().build()
            AppOpenAd.load(
                context,
                "ca-app-pub-3940256099942544/9257395921",
                request,
                object : AppOpenAd.AppOpenAdLoadCallback() {

                    override fun onAdLoaded(ad: AppOpenAd) {
                        Log.d("admobopen","loaded")
                        appOpenAd = ad
                        isLoadingAd = false
                        loadTime = Date().time
                    }


                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                        Log.d("admobopen","failed"+loadAdError.message)

                        isLoadingAd = false
                    }
                }
            )
        }

        /** Check if ad was loaded more than n hours ago. */
        private fun wasLoadTimeLessThanNHoursAgo(): Boolean {
            val dateDifference: Long = Date().time - loadTime
            val numMilliSecondsPerHour: Long = 3600000
            return dateDifference < numMilliSecondsPerHour * 4
        }

        /** Check if ad exists and can be shown. */
        private fun isAdAvailable(): Boolean {

            return appOpenAd != null && wasLoadTimeLessThanNHoursAgo()
        }


        fun showAdIfAvailable(activity: Activity) {
            showAdIfAvailable(
                activity,
                object : OnShowAdCompleteListener {
                    override fun onShowAdComplete() {
                        // Empty because the user will go back to the activity that shows the ad.
                    }
                }
            )
        }


        fun showAdIfAvailable(
            activity: Activity,
            onShowAdCompleteListener: OnShowAdCompleteListener
        ) {
            // If the app open ad is already showing, do not show the ad again.
            if (isShowingAd) {
                return
            }

            // If the app open ad is not available yet, invoke the callback then load the ad.
            if (!isAdAvailable()) {
                onShowAdCompleteListener.onShowAdComplete()
                loadAd(activity)
                return
            }

            appOpenAd!!.fullScreenContentCallback = object : FullScreenContentCallback() {
                /** Called when full screen content is dismissed. */
                override fun onAdDismissedFullScreenContent() {
                    // Set the reference to null so isAdAvailable() returns false.
                    appOpenAd = null
                    isShowingAd = false
                    onShowAdCompleteListener.onShowAdComplete()
                    loadAd(activity)
                }

                /** Called when fullscreen content failed to show. */
                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    Log.d("admobopen","failed"+adError.message)

                    appOpenAd = null
                    isShowingAd = false
                    onShowAdCompleteListener.onShowAdComplete()
                    loadAd(activity)
                }

                /** Called when fullscreen content is shown. */
                override fun onAdShowedFullScreenContent() {
                    Log.d("shown","show")

                }
            }
            isShowingAd = true
            appOpenAd!!.show(activity)
        }
    }


    override fun onActivityCreated(p0: Activity, p1: Bundle?) {

    }

    override fun onActivityStarted(p0: Activity) {
        if (!appOpenAdManager.isShowingAd) {
            currentActivity = p0
        }
    }

    override fun onActivityResumed(p0: Activity) {

    }

    override fun onActivityPaused(p0: Activity) {

    }

    override fun onActivityStopped(p0: Activity) {

    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {

    }

    override fun onActivityDestroyed(p0: Activity) {

    }


}