package com.example.livebasketballcompose.ui.activities

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.example.livebasketballcompose.R
import com.example.livebasketballcompose.appads.AdManager
import com.example.livebasketballcompose.appinterfaces.AdManagerListener
import com.example.livebasketballcompose.ui.theme.LiveBasketballComposeTheme
import com.example.livebasketballcompose.ui.theme.PlayerScreenComposeTheme
import com.example.livebasketballcompose.utils.AppConstants
import com.example.livebasketballcompose.utils.ExoPlayerManager
import com.example.livebasketballcompose.utils.StreamingUtils
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

class PlayerScreen : ComponentActivity(),AdManagerListener {
    private var orientationEventListener: OrientationEventListener? = null
    private var isLockMode: Boolean = false
    private var exoPlayer: ExoPlayer? = null
    private var baseLink: String = ""
    private var playerError by mutableStateOf(false)
    private var mode by mutableStateOf(false)
    private var playerView: PlayerView? = null
    private var isClicked by mutableStateOf(true)
    var key =1
    private var adManager: AdManager? = null
    private var adStatus = false


    @UnstableApi
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.light(
                Color.Transparent.toArgb(), Color.Transparent.toArgb()
            )
        )
        super.onCreate(savedInstanceState)
        adManager = AdManager(this, this, this)

        setContent {
            PlayerScreenComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
                ) {

                    if (intent != null) {
                        val link = intent.getStringExtra("linkAppend")
                        baseLink = intent.getStringExtra("baseLink").toString()

                        Column(modifier = Modifier.background(color = Color.Black)) {
                            buildExoplayer(link)
                            if (playerError) {
                                againSetUpExoplayer()
                            }
                        }

                    }


                }
            }
        }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                if (adStatus) {
                    if (!AppConstants.locationAfter.equals("none", true)) {
                        adManager?.showAds(AppConstants.locationAfter)
                    }
                } else {
//                    AppConstants.videoFinish = true
                    finish()
                }


            }
        })

        changeOrientation()
    }

    private fun changeOrientation() {
        Thread {
            orientationEventListener =
                object : OrientationEventListener(this) {
                    override fun onOrientationChanged(orientation: Int) {
                        val leftLandscape = 90
                        val rightLandscape = 270
                        runOnUiThread {
                            if (epsilonCheck(orientation, leftLandscape) ||
                                epsilonCheck(orientation, rightLandscape)
                            ) {
                                if (!isLockMode)
                                    requestedOrientation =
                                        ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                            } else {
                                if (!isLockMode) {
                                    if (orientation in 0..45 || orientation >= 315 || orientation in 135..225) {
                                        requestedOrientation =
                                            ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                                    }

                                }
                            }
                        }

                    }

                    private fun epsilonCheck(a: Int, b: Int): Boolean {
                        return a > b - 10 && a < b + 10
                    }
                }
            orientationEventListener?.enable()

        }.start()

    }


    @androidx.annotation.OptIn(UnstableApi::class)
    @Composable
    private fun buildExoplayer(link: String?) {

        // Obtain the current context and lifecycle owner using LocalContext and LocalLifecycleOwner
        val context = LocalContext.current
        val lifecycleOwner = LocalLifecycleOwner.current
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mode = true
            playerView?.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL

        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            mode = false
            playerView?.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT

        }

        // Remember the ExoPlayer instance to persist across recompositions
        exoPlayer = remember { ExoPlayerManager.getExoPlayer(context) }

        // Launch an effect to initialize ExoPlayer and set up the media source
        LaunchedEffect(key1 = Unit) {

            adManager?.loadAdProvider(
                AppConstants.locationAfter, AppConstants.adAfter,
                null, null, null, null
            )
            Log.d("launches","first")
            // Create a data source factory for handling media requests
            val dataSourceFactory = DefaultHttpDataSource.Factory()

            // Define the URI for the sample HLS stream
            val uri = Uri.Builder()
                .encodedPath(link)
                .build()
            val mediaItem =
                MediaItem.Builder().setMimeType(MimeTypes.APPLICATION_M3U8).setUri(uri).build()

            // Create an HlsMediaSource from the media item for handling HTTP Live Streaming (HLS) content
            val internetVideoSource =
                HlsMediaSource.Factory(dataSourceFactory).createMediaSource(mediaItem)

            exoPlayer?.setMediaSource(internetVideoSource)
            exoPlayer?.prepare()
            exoPlayer?.playWhenReady = true
//            exoPlayer?.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING

            // Will be used in later implementation for Equalizer
            exoPlayer?.addListener(object : Player.Listener {
                override fun onPlayerError(error: PlaybackException) {
                    playerError = true
                }


            })

        }
        // ...

        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
                    .background(Color.Black)
                    .align(Alignment.Center)
                    .clickable {
                        if (isClicked) {
                            isClicked = false
                        } else {
                            isClicked = true
                        }
                    },
                factory = {
                    PlayerView(context).apply {
                        // Connect the ExoPlayer instance to the PlayerView
                        player = exoPlayer
                        playerView = this
                        keepScreenOn=true

                        setShowBuffering(PlayerView.SHOW_BUFFERING_WHEN_PLAYING)
                        resizeMode =
                            if (mode) AspectRatioFrameLayout.RESIZE_MODE_FILL else AspectRatioFrameLayout.RESIZE_MODE_FIT

                        // Configure ExoPlayer settings
                        exoPlayer?.repeatMode = Player.REPEAT_MODE_ONE
                        exoPlayer?.playWhenReady = false
                        layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                        useController = false

                    }
                }
            )
            AnimatedVisibility(
                visible = isClicked, modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .padding(bottom = 60.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.record),
                            contentDescription = "live Icon",
                            modifier = Modifier
                                .size(16.dp)
                                .padding(start = 10.dp)
                        )
                        Text(
                            text = "Live",
                            color = Color.White,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                    LiveProgressBar()
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Icon(painter = painterResource(id = R.drawable.ic_locked),
                            contentDescription = "locked icon",
                            tint = Color.White,
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .clickable {

                                })
                        Spacer(modifier = Modifier.weight(1f))
//                    Icon(
//                        painter = painterResource(id = R.drawable.fit_mode),
//                        contentDescription = "fit mode",
//                        tint = Color.White,
//                        modifier = Modifier
//                            .size(40.dp)
//                            .padding(end = 10.dp)
//                            .clickable {
//
//                            }
//                    )
                    }

                }

            }

            AdmobBannerTop(modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
                .align(Alignment.TopEnd))
            AdmobBannerTop(modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp)
                .align(Alignment.BottomEnd))

        }

        // ...
        // Observe lifecycle events (e.g., app resume and pause)
        // and adjust ExoPlayer's playback state accordingly.
        DisposableEffect(key1 = lifecycleOwner) {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_RESUME) {
                    exoPlayer?.playWhenReady = true
                } else if (event == Lifecycle.Event.ON_PAUSE) {
                    exoPlayer?.playWhenReady = false
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }

        // Release the ExoPlayer when the composable is disposed
        // This helps in proper resource management
        DisposableEffect(key1 = Unit) {
            onDispose { ExoPlayerManager.releaseExoPlayer() }
        }
        // ...
    }

    @Composable
    fun AdmobBannerTop(modifier: Modifier) {
        AndroidView(
            modifier = modifier,
            factory = { context ->

                // on below line specifying ad view.
                AdView(context).apply {
                    // on below line specifying ad size
                    //adSize = AdSize.BANNER
                    // on below line specifying ad unit id
                    // currently added a test ad unit id.
//                    setAdSize(AdSize.BANNER)
//                    adUnitId = "ca-app-pub-3940256099942544/6300978111"
//                    // calling load ad to load our ad.
//                    loadAd(AdRequest.Builder().build())
                }
            },


        )
    }


    @androidx.annotation.OptIn(UnstableApi::class)
    @Composable
    private fun againSetUpExoplayer() {

//        if (exoPlayer != null) {
//            ExoPlayerManager.releaseExoPlayer()
//        }

        playerError = false
        if (!baseLink.isNullOrEmpty()) {

            val token = StreamingUtils.improveDeprecatedCode(baseLink)
            val path = baseLink + token

            buildExoplayer(link = path)
        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemUI()

    }

    private fun hideSystemUI() {
        // Set the content to appear under the system bars so that the content
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.decorView.let {
            WindowInsetsControllerCompat(window, it).let { controller ->
                controller.hide(WindowInsetsCompat.Type.systemBars())
                controller.systemBarsBehavior =
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun LiveProgressBar() {
        val interactionSource = remember { MutableInteractionSource() }

        Slider(
            value = 100f,
            onValueChange = { },

            track = {
                SliderDefaults.Track(
                    modifier = Modifier.scale(scaleX = 1f, scaleY = 0.5f),
                    sliderState = it,
                    colors = SliderDefaults.colors(
                        thumbColor = Color.Red,
                        activeTrackColor = Color.Red,
                        inactiveTrackColor = Color.Red,
                    )
                )
            },
            thumb = {
                SliderDefaults.Thumb(
                    modifier = Modifier.scale(scaleX = 0.5f, scaleY = 0.5f),
                    interactionSource = interactionSource, colors = SliderDefaults.colors(
                        thumbColor = Color.Red
                    )
                )
            }

        )
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val orientation = newConfig.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            mode = false
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mode = true
        }

//        Log.d("playback error", "again came"+newConfig.toString())

    }

    override fun onAdLoad(value: String) {
        adStatus = value.equals("success", true)

    }

    override fun onAdFinish() {
        finish()
    }

}