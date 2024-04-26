package com.example.livebasketballcompose.ui.activities

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.livebasketballcompose.R
import com.example.livebasketballcompose.ui.theme.ColorPrimary
import com.example.livebasketballcompose.ui.theme.ColorPrimaryDark
import com.example.livebasketballcompose.ui.theme.LiveBasketballComposeTheme
import com.example.livebasketballcompose.utils.CodeUtils
import com.example.livebasketballcompose.utils.CodeUtils.checkDeviceRootedOrNot
import com.example.livebasketballcompose.utils.CodeUtils.checkInternetIsAvailable
import com.example.livebasketballcompose.utils.CodeUtils.checkRunningDeviceIsReal
import com.example.livebasketballcompose.utils.CodeUtils.isNotificationPermissionGranted
import kotlinx.coroutines.delay

class SplashScreen : ComponentActivity() {
    private var requestPermissionCount = 0
    private var isPermissionGranted by mutableStateOf(false)
    private var isPermissionGrantedNotGiven by mutableStateOf(false)

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                isPermissionGranted = true
//                bindingSplash?.notificationLayout?.visibility = View.GONE
                checkNecessities()
                // Permission is granted. Continue the action or workflow in your
            } else {
                isPermissionGrantedNotGiven = true
//                bindingSplash?.notificationLayout?.visibility = View.VISIBLE
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiveBasketballComposeTheme {
                Surface {
                    Box(modifier = Modifier.fillMaxSize()) {
                        ShowSplashImage()

                        var show by remember {
                            mutableStateOf(1.0f)
                        }
                        if (checkInternetIsAvailable(LocalContext.current)) {
                            LaunchedEffect(key1 = Unit) {
                                delay(2000)
//                                show = 0.0f
//                                navigateToNextScreen()
                                checkNecessities()

                            }
                        } else {

                        }
                        if (isPermissionGranted) {
                            show = 0.0f
                            subscribeToTopicForNotification()
                        } else {
                            if (isPermissionGrantedNotGiven) {

                                show = 0.0f
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(color = ColorPrimaryDark),
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.app_icon),
                                        contentDescription = "app icon",
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                            .size(300.dp)
                                            .padding(top = 70.dp)
                                    )
                                    Text(
                                        text = "Notifications",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                            .padding(top = 50.dp)
                                    )

                                    Text(
                                        text = "Get Notified about the latest updates, and upcoming matches to stay ahead of games," +
                                                "Sound Great!",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp,
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                            .padding(top = 20.dp),
                                        textAlign = TextAlign.Center
                                    )
                                    TextButton(
                                        onClick = {
                                            makeNotificationPermission()
                                        }, colors = ButtonColors(
                                            containerColor = ColorPrimary,
                                            contentColor = Color.White,
                                            disabledContainerColor = ColorPrimary,
                                            disabledContentColor = Color.White
                                        ),
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                            .padding(top = 40.dp)
                                    ) {
                                        Text(text = " Turn on Notifications ", fontSize = 20.sp)
                                    }

                                    TextButton(
                                        onClick = {
                                            subscribeToTopicForNotification()
                                        }, colors = ButtonColors(
                                            containerColor = ColorPrimary,
                                            contentColor = Color.White,
                                            disabledContainerColor = ColorPrimary,
                                            disabledContentColor = Color.White
                                        ),
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                            .padding(top = 20.dp)
                                    ) {
                                        Text(text = "       Later     ", fontSize = 20.sp)
                                    }
                                }
                            }
                        }



                        CircularProgressIndicator(
                            modifier = Modifier
                                .width(50.dp)
                                .alpha(show)
                                .padding(bottom = 150.dp)
                                .align(Alignment.BottomCenter),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
    private fun subscribeToTopicForNotification() {
        navigateToNextScreen()
//        val preference = StreamingPreference(this)
//        val getStatus = preference.getNotificationStatus(preferenceKey)
//        if (getStatus == true) {
//            navigateToMainUiScreen()
//            ///means already subscribe to topic...
//        } else {
//            FirebaseMessaging.getInstance().subscribeToTopic("event")
//                .addOnCompleteListener { task ->
//                    if (task.isComplete) {
//                        //
//                        preference.saveNotificationStatus(preferenceKey, true)
//
//                    }
//                }
//            navigateToMainUiScreen()
//        }
    }


    override fun onResume() {
        super.onResume()
//        checkNecessities()
    }

    private fun makeNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(
                Manifest.permission.POST_NOTIFICATIONS
            )
        }

        requestPermissionCount++
    }

    private fun checkNecessities() {
        if (!checkRunningDeviceIsReal()) {
            if (!checkDeviceRootedOrNot(this@SplashScreen)) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (isNotificationPermissionGranted(this@SplashScreen)) {
                        isPermissionGranted = true
                    } else {
                        makeNotificationPermission()
                    }

                } else {
                    isPermissionGranted = true
                }
            }
        } else {

        }
    }

    private fun navigateToNextScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


    @Composable
    private fun ShowSplashImage() {
        Image(
            painterResource(id = R.drawable.splash),
            contentDescription = "splash image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}