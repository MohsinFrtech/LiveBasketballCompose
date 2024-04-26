package com.example.livebasketballcompose.ui.activities

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.Observer
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.livebasketballcompose.R
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.livebasketballcompose.appads.AdManager
import com.example.livebasketballcompose.appinterfaces.AdManagerListener
import com.example.livebasketballcompose.appinterfaces.CppSuccessListener
import com.example.livebasketballcompose.composescreens.CountryWithLeagues
import com.example.livebasketballcompose.composescreens.channelScreen
import com.example.livebasketballcompose.composescreens.channelSelected
import com.example.livebasketballcompose.composescreens.localVal
import com.example.livebasketballcompose.composescreens.moreScreen
import com.example.livebasketballcompose.composescreens.navigateToPlayerScreen
import com.example.livebasketballcompose.composescreens.showLeagues
import com.example.livebasketballcompose.composescreens.showMatchDescription
import com.example.livebasketballcompose.composescreens.showNotificationScreen
import com.example.livebasketballcompose.composescreens.showScoresAndMatches
import com.example.livebasketballcompose.cppfiles.NativeClass
import com.example.livebasketballcompose.models.DataModel
import com.example.livebasketballcompose.models.Event
import com.example.livebasketballcompose.navigation.BottomNavItem
import com.example.livebasketballcompose.networklayer.ApiState
import com.example.livebasketballcompose.ui.theme.ColorPrimary
import com.example.livebasketballcompose.ui.theme.ColorPrimaryDark
import com.example.livebasketballcompose.ui.theme.LiveBasketballComposeTheme
import com.example.livebasketballcompose.utils.AppConstants
import com.example.livebasketballcompose.utils.AppConstants.selectedRoute
import com.example.livebasketballcompose.viewmodels.BasketScoreViewModel
import com.example.livebasketballcompose.viewmodels.StreamingViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList
import java.util.Locale

class MainActivity : ComponentActivity(), CppSuccessListener, AdManagerListener {
    private var nativeClass: NativeClass? = null
    private var navController: NavHostController? = null
    private var isClicked by mutableStateOf(false)
    private var isClickedSearch by mutableStateOf(false)
    private var backPressedStreaming by mutableStateOf(false)
    private var backBoolean = false
    lateinit var shouldShowDialog: MutableState<Boolean>
    private var adManager: AdManager? = null

    //    private val streamingViewModel by lazy {
//        ViewModelProvider(this)[StreamingViewModel::class.java]
//    }
    var streamingViewModel: StreamingViewModel? = null
    var scoreViewModel: BasketScoreViewModel? = null
    private var adProviderName = "none"
    private var adStatus = false
    private var selectedEventName = ""
    private var selectedPriority = 0


    var liveEvents: MutableList<Event> =
        ArrayList<Event>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nativeClass = NativeClass(this, this)
        adManager = AdManager(this, this, this)
        setContent {
            LiveBasketballComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    shouldShowDialog = remember { mutableStateOf(false) }

                    CreateBottomNavigationBar()
                    if (backPressedStreaming) {
                        MinimalDialog()
                    }
                }
            }
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                if (backBoolean) {
                    if (!isFinishing) {
                        backPressedStreaming = true
                        shouldShowDialog.value = false
                    }
                } else {
                    backPressedStreaming = false
                    navController?.popBackStack()
                }

            }
        })
    }

    @Composable
    fun MinimalDialog() {

        if (shouldShowDialog.value) return

        Dialog(onDismissRequest = { shouldShowDialog.value = true }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(0.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.app_icon),
                        contentDescription = "icon",
                        modifier = Modifier
                            .width(120.dp)
                            .height(120.dp)
                            .padding(top = 10.dp, bottom = 10.dp)
                    )
                    Text(text = "App Rating", color = Color.Black)
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp)
                    ) {
                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 10.dp), onClick = {
                                shouldShowDialog.value = true
                                rateUsFunction()
                            }, shape = RoundedCornerShape(10.dp), colors = ButtonColors(
                                containerColor = ColorPrimary,
                                contentColor = Color.White,
                                disabledContentColor = Color.White,
                                disabledContainerColor = ColorPrimary
                            )
                        ) {
                            Text(text = "Rate Us")
                        }
                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 10.dp), onClick = {
                                shouldShowDialog.value = true
                                finishAffinity()
                            }, shape = RoundedCornerShape(10.dp), colors = ButtonColors(
                                containerColor = ColorPrimary,
                                contentColor = Color.White,
                                disabledContentColor = Color.White,
                                disabledContainerColor = ColorPrimary
                            )
                        ) {
                            Text(text = "Exit")
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun CreateBottomNavigationBar() {
        navController = rememberNavController()
        val list = getBottomNavigationItems()
        Scaffold(
            bottomBar = {
                AppNavigationBar(navController!!, list)
            }
        ) {
            it.calculateBottomPadding()
            NavigationConfiguration(navController!!)

        }
    }

    @Composable
    private fun NavigationConfiguration(navController: NavHostController) {
        NavHost(navController = navController, startDestination = BottomNavItem.streaming.route,
            enterTransition = {
                EnterTransition.None
            }, exitTransition = {
                ExitTransition.None
            }) {

            composable(BottomNavItem.streaming.route) {
                backBoolean = true
                StreamingScreen()
            }
            composable(BottomNavItem.score.route) {
                backBoolean = false
                showScoresAndMatches(navController)
            }
            composable(BottomNavItem.league.route) {
                backBoolean = false
                showLeagues(navController)
            }
            composable(BottomNavItem.more.route) {
                backBoolean = false
                moreScreen(LocalContext.current)
            }
            composable(BottomNavItem.channelScreen.route + "/{eventName}/{priority}") {
                backBoolean = false
                val param = it.arguments?.getString("eventName")
                val param2 = it.arguments?.getString("priority")

                channelScreen(param, param2, LocalContext.current, navController, adManager)
            }
            composable(BottomNavItem.notificationScreen.route) {
                backBoolean = false
                showNotificationScreen()
            }
            composable(BottomNavItem.matchDescription.route) {
                backBoolean = false
                showMatchDescription()
            }
            composable(BottomNavItem.countryLeagues.route) {
                backBoolean = false
                CountryWithLeagues(navController)
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun StreamingScreen() {
        val textState = remember { mutableStateOf(TextFieldValue("")) }
        var show by remember {
            mutableStateOf(false)
        }
        streamingViewModel = viewModel(LocalContext.current as ComponentActivity)
        scoreViewModel = viewModel(LocalContext.current as ComponentActivity)

        val refreshing by streamingViewModel!!.isRefreshing.collectAsStateWithLifecycle()
        val pullRefreshState =
            rememberPullRefreshState(refreshing, { streamingViewModel!!.getLiveEventsFromRemote() })

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = ColorPrimaryDark)
                .pullRefresh(pullRefreshState)

        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
                    colors = CardDefaults.cardColors(containerColor = ColorPrimary)
                ) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, bottom = 5.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AnimatedVisibility(visible = !isClickedSearch) {
                                Icon(
                                    painter = painterResource(id = R.drawable.search),
                                    contentDescription = "search Icon",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .height(30.dp)
                                        .clickable {
                                            isClickedSearch = true
                                        }
                                )
                            }
                            AnimatedVisibility(visible = isClickedSearch) {
                                Icon(
                                    painter = painterResource(id = R.drawable.cancel),
                                    contentDescription = "search Icon",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .height(30.dp)
                                        .clickable {
                                            isClickedSearch = false
                                        }
                                )
                            }
                            Image(
                                painter = painterResource(id = R.drawable.titlebar_logo),
                                contentDescription = "logo",
                                modifier = Modifier.weight(1f)
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.notification1),
                                contentDescription = "notification",
                                tint = Color.White,
                                modifier = Modifier
                                    .height(30.dp)
                                    .padding(end = 10.dp)
                                    .clickable {
                                        Log.d("Clikeddddd", "click")
                                        isClicked = true
                                    }
                            )

                        }
                        AnimatedVisibility(visible = isClickedSearch) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 0.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
                                    .height(48.dp)
                            ) {
                                Row(modifier = Modifier.fillMaxSize()) {
                                    searchView(textState)
                                }
                            }
                        }


                    }

                }
                showLiveEventsFromRemote(textState, show)
                if (isClicked == true) {
                    isClicked = false

                    navController?.navigate(BottomNavItem.notificationScreen.route)
                }
            }
            PullRefreshIndicator(
                refreshing = refreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
            AnimatedVisibility(visible = streamingViewModel!!.showSplashScreen) {
                showSplashScreen(streamingViewModel!!)
            }

            AnimatedVisibility(visible = streamingViewModel!!.showSplashScreen) {
                checkAppUpdateDialog(streamingViewModel = streamingViewModel!!)
            }
        }
    }

    @Composable
    private fun checkAppUpdateDialog(streamingViewModel: StreamingViewModel) {
        Column(modifier = Modifier.fillMaxSize()) {
            Dialog(onDismissRequest = {
//               onDismissRequest()
            }) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(0.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.app_icon),
                            contentDescription = "icon",
                            modifier = Modifier
                                .width(120.dp)
                                .height(120.dp)
                                .padding(top = 10.dp, bottom = 10.dp)
                        )
                        Text(
                            text = streamingViewModel.appUpdateText.toString(),
                            color = Color.Black
                        )
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp)
                        ) {
                            Button(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 10.dp), onClick = {
                                    if (streamingViewModel.isPermanent) finishAffinity() else rateUsFunction(

                                    )

                                }, shape = RoundedCornerShape(10.dp), colors = ButtonColors(
                                    containerColor = ColorPrimary,
                                    contentColor = Color.White,
                                    disabledContentColor = Color.White,
                                    disabledContainerColor = ColorPrimary
                                )
                            ) {
                                if (streamingViewModel.isPermanent) Text(text = "Exit") else Text(
                                    text = "No Thanks"
                                )
                            }
                            Button(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 10.dp), onClick = {
                                    rateUsFunction()
                                }, shape = RoundedCornerShape(10.dp), colors = ButtonColors(
                                    containerColor = ColorPrimary,
                                    contentColor = Color.White,
                                    disabledContentColor = Color.White,
                                    disabledContainerColor = ColorPrimary
                                )
                            ) {
                                Text(text = "Update")
                            }
                        }
                    }
                }
            }
        }
    }

    private fun rateUsFunction() {
        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)
                )
            )
        } catch (e: ActivityNotFoundException) {

            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + packageName)
                    )
                )
            } catch (e: ActivityNotFoundException) {
                Log.d("Exception", "" + e.message)
            }

        }
    }


    @Composable
    private fun searchView(state: MutableState<TextFieldValue>) {
        TextField(
            value = state.value, onValueChange = {
                state.value = it
            }, modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    Icons.Default.Search, contentDescription = "Search",
                    modifier = Modifier.size(24.dp)
                )
            }, singleLine = true,
            trailingIcon = {
                if (state.value != TextFieldValue("")) {
                    IconButton(onClick = {
                        state.value = TextFieldValue("")
                    }) {
                        Icon(
                            Icons.Default.Close, contentDescription = "close",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            },
            shape = RectangleShape,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                cursorColor = Color.Black,
                leadingIconColor = Color.Black,
                trailingIconColor = Color.Black,
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }


    @Composable
    private fun showLiveEventsFromRemote(textState: MutableState<TextFieldValue>, show: Boolean) {
        val apiState by streamingViewModel!!.isLoading.collectAsStateWithLifecycle()
        when (apiState) {
            is ApiState.Empty -> {
                LoadingEvents()
            }

            is ApiState.Loading -> {
                LoadingEvents()
            }

            is ApiState.Error -> {

            }

            is ApiState.Success -> {
                showEventsFromRemote(textState, show)
            }
        }
    }

    @Composable
    private fun showEventsFromRemote(textState: MutableState<TextFieldValue>, show: Boolean) {


        val model by streamingViewModel!!.dataModel2.collectAsStateWithLifecycle()
        if (model != null) {
            val dataRetrieved = model as DataModel

            if (!dataRetrieved?.events.isNullOrEmpty()) {
                ShowEvents(it = dataRetrieved?.events, textState)
            }

            LaunchedEffect(key1 = true) {
                if (!dataRetrieved.app_ads.isNullOrEmpty()) {
                    adProviderName =
                        adManager?.checkProvider(dataRetrieved.app_ads!!, AppConstants.adMiddle)
                            .toString()

                    if (!adProviderName.equals(AppConstants.startApp, true)) {
                        adManager?.loadAdProvider(
                            adProviderName, AppConstants.adMiddle,
                            null, null, null, null
                        )
                    }
                }
            }

        }

    }

    @Composable
    private fun showSplashScreen(streamingViewModel: StreamingViewModel) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = ColorPrimary),
        ) {

            Text(
                text = streamingViewModel.splashText.toString(), color = Color.White, modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp), fontSize = 20.sp
            )
            Text(
                text = streamingViewModel.splashHeading.toString(), color = Color.White, modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 30.dp), fontSize = 16.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = { }, colors = ButtonColors(
                    contentColor = Color.White, disabledContentColor = Color.Black,
                    disabledContainerColor = Color.White, containerColor = Color.White
                ),
                shape = RectangleShape,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 70.dp)
            ) {
                Text(text = streamingViewModel.buttonText.toString(), color = Color.Black)
            }
        }

    }

    @Composable
    private fun LoadingEvents() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(color = Color.White)
        }
    }

    @Composable
    private fun ShowEvents(it: List<Event>?, textState: MutableState<TextFieldValue>) {
        var filteredLiveEvents: MutableList<Event> =
            ArrayList<Event>()

        liveEvents.clear()
        it?.forEach { event ->
            if (event.live == true) {
                liveEvents.add(event)
            }
        }
        liveEvents.sortBy { it1 ->
            it1.priority
        }

        LazyVerticalGrid(// content padding
            contentPadding = PaddingValues(
                start = 10.dp,
                top = 10.dp,
                bottom = 60.dp
            ), columns = GridCells.Fixed(2), content = {
                val searchedText = textState.value.text
                filteredLiveEvents = if (searchedText.isEmpty()) {
                    liveEvents
                } else {
                    val resultList = ArrayList<Event>()
                    for (country in liveEvents) {
                        if (country != null) {
                            if (country.name!!.lowercase(Locale.getDefault())
                                    .contains(searchedText.lowercase(Locale.getDefault()))
                            ) {
                                resultList.add(country)
                            }
                        }
                    }
                    resultList
                }
                items(filteredLiveEvents) {
                    showSingleEvent(it = it, onItemClick = { selectedEvent ->
                        run {
                            selectedRoute = BottomNavItem.channelScreen.route
                            selectedEventName = selectedEvent.name.toString()
                            selectedPriority = selectedEvent.priority ?: 0
                            if (!adProviderName.equals("none", true)) {
                                adManager?.showAds(adProviderName)
                            } else {
                                navController?.navigate(BottomNavItem.channelScreen.route + "/${selectedEvent.name}/${selectedEvent.priority ?: 0}")
                            }
                        }
                    })
                }

            })

    }

    @Composable
    private fun showSingleEvent(it: Event, onItemClick: (Event) -> Unit) {
        Column(modifier = Modifier.clickable {
            onItemClick(it)
        }) {
            Card(
                modifier = Modifier
                    .height(180.dp)
                    .padding(bottom = 5.dp, end = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
//                Image(painter = painterResource(id = R.drawable.app_icon), contentDescription = "")
                    AsyncImage(
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clip(CircleShape),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(it.image_url)
                            .build(),
                        contentDescription = "",
                        placeholder = painterResource(id = R.drawable.app_icon),
                        error = painterResource(id = R.drawable.app_icon)
                    )
                }

            }
            Text(
                text = it.name.toString(), color = Color.White, fontSize = 16.sp,
                modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
            )

        }

    }


    private fun getBottomNavigationItems(): List<BottomNavItem> {
        return listOf(
            BottomNavItem.streaming,
            BottomNavItem.score,
            BottomNavItem.league,
            BottomNavItem.more
        )
    }

    @Composable
    private fun AppNavigationBar(navController: NavHostController, list: List<BottomNavItem>) {
//        Log.d("selectedVal", "val")

        BottomNavigation(backgroundColor = ColorPrimary) {
            val currentRoute = currentRoute(navController)

            list.forEach { listItem ->
                val selected = currentRoute == listItem.route
                BottomNavigationItem(selected = currentRoute == listItem.route, onClick = {
                    if (currentRoute != listItem.route) {

                        navController.navigate(listItem.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }, icon = {
                    Icon(
                        painter = painterResource(id = listItem.icon),
                        contentDescription = "",
                        tint = if (selected) Color.White else Color.Blue
                    )
                },

                    label = {
                        Text(
                            text = listItem.labelName,
                            softWrap = false,
                            fontSize = 14.sp,
                            color = if (selected) Color.White else Color.Transparent
                        )
                    },
                    alwaysShowLabel = false
                )
            }
        }
    }

    private fun observeStreamingData() {
        streamingViewModel?.getLiveEventsFromRemote()
        scoreViewModel?.onRefreshSBasketScoresData()
        streamingViewModel?.dataModelList?.observe(this, Observer {
            if (!it.extra_2.isNullOrEmpty()) {
                nativeClass?.replaceChar = "goi"
                nativeClass?.performCalculation(it.extra_2!!)
            }


        })
    }

    @Composable
    private fun currentRoute(navController: NavHostController): String? {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute by remember {
            derivedStateOf {
                navBackStackEntry?.destination?.route ?: "Home"
            }
        }
        return navBackStackEntry?.destination?.route
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResumeAct", "resume")

    }

    override fun onCppSuccess() {
        lifecycleScope.launch(Dispatchers.Main) {
            observeStreamingData()
        }
    }

    override fun onAdLoad(value: String) {
        Log.d("cppSuccess", "succes" + value)
        adStatus = value.equals("success", true)

    }

    override fun onAdFinish() {
        if (selectedRoute != null) {
            if (selectedRoute.equals(BottomNavItem.channelScreen.route, true)) {
                navController?.navigate(selectedRoute + "/${selectedEventName}/${selectedPriority ?: 0}")
            } else if (selectedRoute.equals("player")) {
                channelSelected?.let { navigateToPlayerScreen(it, this) }
            }
        }
    }


}



