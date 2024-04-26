package com.example.livebasketballcompose.composescreens

import android.text.format.DateFormat
import androidx.activity.ComponentActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.livebasketballcompose.R
import com.example.livebasketballcompose.models.Game
import com.example.livebasketballcompose.models.League
import com.example.livebasketballcompose.navigation.BottomNavItem
import com.example.livebasketballcompose.networklayer.ApiState
import com.example.livebasketballcompose.ui.theme.ColorPrimary
import com.example.livebasketballcompose.ui.theme.ColorPrimaryDark
import com.example.livebasketballcompose.utils.AppConstants
import com.example.livebasketballcompose.utils.AppConstants.EXPANSTION_TRANSITION_DURATION
import com.example.livebasketballcompose.utils.CodeUtils.dateAndTime
import com.example.livebasketballcompose.viewmodels.BasketScoreViewModel
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Composable
fun showScoresAndMatches(navController: NavHostController) {
    val viewModel: BasketScoreViewModel = viewModel(LocalContext.current as ComponentActivity)
    val apiState by viewModel.isLoading.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier
            .background(color = ColorPrimaryDark)
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                colors = CardDefaults.cardColors(containerColor = ColorPrimary),
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 20.dp,
                    bottomEnd = 20.dp
                )
            ) {
                Text(
                    text = "Scores", modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp)
                        .align(Alignment.CenterHorizontally),
                    color = Color.White, fontSize = 18.sp
                )
            }
            when (apiState) {
                is ApiState.Empty -> {
                    LoadingMatches()
                }

                is ApiState.Loading -> {
                    LoadingMatches()
                }

                is ApiState.Error -> {

                    LoadingMatches()
                }

                is ApiState.Success -> {
                    val countryLeagueList by viewModel.countryLeagueList.collectAsStateWithLifecycle()
                    val expandedCardIds by viewModel.expandedCardIdsList.collectAsStateWithLifecycle()
                    val leagueList: MutableList<League> =
                        ArrayList()
                    if (countryLeagueList != null) {
                        if (!countryLeagueList!!.countries.isNullOrEmpty()) {
                            countryLeagueList?.countries?.forEach {
                                if (!it.leagues.isNullOrEmpty()) {
                                    leagueList.addAll(it.leagues)

                                }

                            }
                        }

                    }
                    if (!leagueList.isNullOrEmpty()) {
                        LazyColumn(
                            modifier = Modifier.padding(top = 10.dp),
                            contentPadding = PaddingValues(
                                bottom = 60.dp
                            )
                        ) {

                            items(leagueList) { list ->
                                ShowMatchesAndScores(
                                    navController,

                                    onCardArrowClick = {
                                        list.id?.let {
                                            viewModel.onCardArrowClicked(
                                                it
                                            )
                                        }
                                    },
                                    expanded = expandedCardIds.contains(list.id),
                                    list,
                                )
                            }
                        }

                    }

                }
            }


        }
    }


}

@Composable
private fun LoadingMatches() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(color = Color.White)
    }
}

@Composable
fun ShowMatchesAndScores(
    navController: NavHostController, onCardArrowClick: () -> Unit,
    expanded: Boolean,
    league: League,
) {
    val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState = !expanded
        }
    }
    val transition = updateTransition(transitionState, label = "")
    val arrowRotationDegree by transition.animateFloat({
        tween(durationMillis = AppConstants.EXPANSTION_TRANSITION_DURATION)
    }, label = "") {
        it.not()
        if (expanded) 90f else 0f
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = 15.dp,
                start = 15.dp, end = 15.dp
            )
            .clickable {
                onCardArrowClick()
            },
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = ColorPrimary)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                modifier = Modifier
                    .size(60.dp)
                    .padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(league.games?.get(0)?.league?.logo.toString())
                    .build(),
                contentDescription = "",
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder)
            )
            Text(
                text = league.name.toString(),
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            )
            Icon(
                painter = painterResource(id = R.drawable.forward),
                contentDescription = "next",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 10.dp)
                    .rotate(arrowRotationDegree),
                tint = Color.White
            )
        }
        if (!league.games.isNullOrEmpty()) {
            ExpandableContent(
                visible = expanded,
                initialVisibility = expanded,
                league.games,
                navController
            )
        }
    }


}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ExpandableContent(
    visible: Boolean = true,
    initialVisibility: Boolean = false,
    games: List<Game>?,
    navController: NavHostController
) {
    val viewModel: BasketScoreViewModel = viewModel(LocalContext.current as ComponentActivity)

    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(EXPANSTION_TRANSITION_DURATION)
        ) + fadeIn(
            initialAlpha = 0.3f,
            animationSpec = tween(EXPANSTION_TRANSITION_DURATION)
        )
    }
    val exitTransition = remember {
        shrinkVertically(
            // Expand from the top.
            shrinkTowards = Alignment.Top,
            animationSpec = tween(EXPANSTION_TRANSITION_DURATION)
        ) + fadeOut(
            // Fade in with the initial alpha of 0.3f.
            animationSpec = tween(EXPANSTION_TRANSITION_DURATION)
        )
    }
    AnimatedVisibility(
        visible = visible,
        initiallyVisible = initialVisibility,
        enter = enterTransition,
        exit = exitTransition
    ) {
        val height = games!!.size.times(130)

        LazyColumn(
            modifier = Modifier
                .height(height.dp)
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            items(games!!) { game ->
                val gameDate = dateAndTime(game.date)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                        .clickable {
                            viewModel.selectedGame = game
                            navController.navigate(BottomNavItem.matchDescription.route)
                        }, colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f), horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AsyncImage(
                                    modifier = Modifier
                                        .size(80.dp),
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(game.teams?.away?.logo?.toString())
                                        .build(),
                                    contentDescription = "first team image",
                                    placeholder = painterResource(id = R.drawable.app_icon),
                                    error = painterResource(id = R.drawable.app_icon)
                                )
                                Text(
                                    text = game.teams?.away?.name.toString(),
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    modifier = Modifier
                                        .padding(start = 0.dp, bottom = 5.dp),
                                    lineHeight = 15.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1.5f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = game.status?.longName.toString(),
                                    color = Color.Red,
                                    modifier = Modifier.padding(top = 0.dp),
                                    fontSize = 13.sp,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = if (!game.status?.short.equals("NS", true))
                                        "" + game.scores?.home?.total + "  VS  " + game.scores?.away?.total else "VS",
                                    color = Color.Black,
                                    modifier = Modifier.padding(top = 0.dp),
                                    fontSize = 18.sp,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = gameDate.toString(),
                                    color = Color.Black,
                                    modifier = Modifier.padding(top = 0.dp),
                                    lineHeight = 15.sp,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f), horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AsyncImage(
                                    modifier = Modifier
                                        .size(80.dp),
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(game?.teams?.home?.logo.toString())
                                        .build(),
                                    contentDescription = "second team image",
                                    placeholder = painterResource(id = R.drawable.app_icon),
                                    error = painterResource(id = R.drawable.app_icon)
                                )
                                Text(
                                    text = game?.teams?.home?.name.toString(),
                                    color = Color.Black,
                                    lineHeight = 15.sp,
                                    fontSize = 14.sp,
                                    modifier = Modifier
                                        .padding(start = 0.dp, bottom = 5.dp),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}
