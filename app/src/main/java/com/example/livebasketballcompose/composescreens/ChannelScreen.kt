package com.example.livebasketballcompose.composescreens

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.livebasketballcompose.R
import com.example.livebasketballcompose.appads.AdManager
import com.example.livebasketballcompose.models.Channel
import com.example.livebasketballcompose.models.DataModel
import com.example.livebasketballcompose.navigation.BottomNavItem
import com.example.livebasketballcompose.ui.activities.PlayerScreen
import com.example.livebasketballcompose.ui.theme.ColorPrimary
import com.example.livebasketballcompose.ui.theme.ColorPrimaryDark
import com.example.livebasketballcompose.utils.AppConstants
import com.example.livebasketballcompose.utils.StreamingUtils
import com.example.livebasketballcompose.viewmodels.StreamingViewModel
import se.simbio.encryption.Encryption
import java.util.ArrayList

var localVal = ""
var channelSelected:Channel?=null
var adProviderChannel=""
@Composable
fun channelScreen(
    eventName: String?,
    eventPriority: String?,
    current: Context,
    navController: NavHostController,
    adManager: AdManager?
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = ColorPrimaryDark)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = CardDefaults.cardColors(containerColor = ColorPrimary),
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomStart = 20.dp,
                bottomEnd = 20.dp
            )
        ) {
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.back_latest),
                    contentDescription = "back button",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )
                Text(
                    text = eventName.toString(),
                    color = Color.White,
                    modifier = Modifier.padding(start = 20.dp), fontSize = 18.sp
                )
            }
        }
        showLiveChannels(eventName, eventPriority, current, adManager)
    }
}

@Composable
fun showLiveChannels(
    eventName: String?,
    eventPriority: String?,
    current: Context,
    adManager: AdManager?
) {

    val viewModel: StreamingViewModel = viewModel(LocalContext.current as ComponentActivity)
    if (viewModel != null) {

        val model by viewModel.dataModel2.collectAsState()
        val dataRetrieved = model as DataModel

        if (!dataRetrieved?.extra_3.isNullOrEmpty()) {
            localVal = dataRetrieved?.extra_3.toString()
        }
        if (!dataRetrieved?.events.isNullOrEmpty()) {
            dataRetrieved?.events?.forEach { event ->
                if (event.live == true) {
                    if (event.name?.equals(eventName, true) == true) {
                        if (event.priority == eventPriority?.toInt()) {
                            if (!event.channels.isNullOrEmpty()) {
                                showChannels(event.channels!!, current,adManager)
                            }
                        }
                    }
                }
            }
        }

        LaunchedEffect(key1 = true) {
            if (!dataRetrieved.app_ads.isNullOrEmpty()) {
                val adProviderName =
                    adManager?.checkProvider(dataRetrieved.app_ads!!, AppConstants.adBefore)
                        .toString()
                adProviderChannel = adProviderName
                AppConstants.locationAfter =
                    adManager?.checkProvider(dataRetrieved.app_ads!!, AppConstants.adAfter).toString()
                if (!adProviderName.equals(AppConstants.startApp, true)) {
                    adManager?.loadAdProvider(
                        adProviderName, AppConstants.adBefore,
                        null, null, null, null
                    )
                }
            }
        }
    }
}

@Composable
fun showChannels(channels: List<Channel>, current: Context, adManager: AdManager?) {
    var liveChannels: MutableList<Channel> =
        ArrayList<Channel>()
    channels?.forEach { channel ->
        if (channel.live == true) {
            liveChannels.add(channel)
        }
    }
    liveChannels.sortBy { it1 ->
        it1.priority
    }
    LazyColumn(// content padding
        contentPadding = PaddingValues(
            start = 10.dp,
            top = 10.dp,
            bottom = 60.dp
        )
    ) {
        items(liveChannels) { singleChannel ->
            showSingleChannelItem(singleChannel, onItemClick = { channel ->
                channelSelected =channel
                AppConstants.selectedRoute= "player"
                if (!adProviderChannel.equals("none",true)) {
                    adManager?.showAds(adProviderChannel)
                }
                else
                {
                    navigateToPlayerScreen(channel, current)
                }

            })
        }
    }

}

fun navigateToPlayerScreen(selectedChannel: Channel, current: Context) {
    try {

        if (selectedChannel?.channel_type.equals(
                AppConstants.typeFlussonic, true
            )
        ) {

            if (localVal.isNotEmpty()) {
                AppConstants.parsedString = getChannelType(localVal)
            }
            val token = selectedChannel.url?.let { it1 ->
                StreamingUtils.improveDeprecatedCode(it1)
            }
            val linkAppend = selectedChannel.url + token
            val intent = Intent(current, PlayerScreen::class.java)
            intent.putExtra("baseLink", selectedChannel.url)
            intent.putExtra("linkAppend", linkAppend)
            current.startActivity(intent)
        }

    } catch (e: Exception) {
        Log.d("Token", "exception" + e.message)
    }
}

fun getChannelType(strToDecrypt: String?): String {
    val iv = ByteArray(AppConstants.mySecretSize)
    val encryption: Encryption = Encryption.getDefault(
        AppConstants.myUserLock1,
        AppConstants.myUserCheck1, iv
    )

    return encryption.decryptOrNull(strToDecrypt)
}

@Composable
fun showSingleChannelItem(singleChannel: Channel, onItemClick: (Channel) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            .clickable {
                onItemClick(singleChannel)
            },
        colors = CardDefaults.cardColors(containerColor = ColorPrimary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
//                Image(painter = painterResource(id = R.drawable.app_icon), contentDescription = "")
            AsyncImage(
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(170.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(singleChannel.image_url)
                    .build(),
                contentDescription = "",
                placeholder = painterResource(id = R.drawable.placeholder_large),
                error = painterResource(id = R.drawable.placeholder_large)
            )
            Text(
                text = singleChannel.name.toString(), color = Color.White, fontSize = 16.sp,
                modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
            )
        }

    }
}


