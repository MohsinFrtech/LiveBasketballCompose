package com.example.livebasketballcompose.composescreens

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
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.modifier.modifierLocalConsumer
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
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.livebasketballcompose.R
import com.example.livebasketballcompose.models.Country
import com.example.livebasketballcompose.models.Game
import com.example.livebasketballcompose.models.League
import com.example.livebasketballcompose.navigation.BottomNavItem
import com.example.livebasketballcompose.networklayer.ApiState
import com.example.livebasketballcompose.ui.theme.ColorPrimary
import com.example.livebasketballcompose.ui.theme.ColorPrimaryDark
import com.example.livebasketballcompose.utils.AppConstants
import com.example.livebasketballcompose.viewmodels.BasketScoreViewModel
import java.util.ArrayList

@Composable
fun showLeagues(navController: NavHostController) {

    Column(
        modifier = Modifier
            .background(color = ColorPrimaryDark)
            .fillMaxSize()
    ) {
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
                text = "Leagues",
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
                    .align(Alignment.CenterHorizontally),
                color = Color.White,
                fontSize = 18.sp
            )


        }
        ShowAllCountriesFromRemote(navController)
    }
}

@Composable
fun ShowAllCountriesFromRemote(navController: NavHostController) {
    val countryList: MutableList<Country> =
        ArrayList()
    val viewModel: BasketScoreViewModel = viewModel(LocalContext.current as ComponentActivity)
    val countryLeagueList by viewModel.countryLeagueList.collectAsStateWithLifecycle()
    val expandedCardIds by viewModel.expandedCountryIdsList.collectAsStateWithLifecycle()
    val apiState by viewModel.isLoading.collectAsStateWithLifecycle()
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
            if (viewModel != null) {
                if (countryLeagueList != null) {
                    if (!countryLeagueList!!.countries.isNullOrEmpty()) {
                        countryLeagueList!!.countries?.let { countryList.addAll(it) }

                        LazyColumn(modifier = Modifier.padding(top = 20.dp),
                            contentPadding = PaddingValues(
                                bottom = 60.dp
                            )
                        ) {
                            items(countryList) { country ->
                                ShowCountryItem(
                                    country, navController, onCardArrowClick = {
                                        viewModel.onCountryArrowClicked(country.id!!)
                                    },
                                    expanded = expandedCardIds.contains(country.id)
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
fun ShowCountryItem(
    country: Country, navController: NavHostController,
    onCardArrowClick: () -> Unit,
    expanded: Boolean,
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
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .decoderFactory(SvgDecoder.Factory())
                        .data(country.flag.toString())
                        .size(Size.ORIGINAL) // Set the target size to load the image at.
                        .build()
                )
                Image(
                    painter=painter,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(4.dp),

                    contentDescription = "",
                )
                Text(
                    text = country.name.toString(),
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
            if (!country.leagues.isNullOrEmpty()) {
                ExpandableContentCountry(
                    visible = expanded,
                    initialVisibility = expanded,
                    country.leagues,
                    navController
                )
            }
        }

    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ExpandableContentCountry(
    visible: Boolean = true,
    initialVisibility: Boolean = false,
    games: List<League>?,
    navController: NavHostController
) {
    val viewModel: BasketScoreViewModel = viewModel(LocalContext.current as ComponentActivity)

    val enterTransition = remember {
        expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = tween(AppConstants.EXPANSTION_TRANSITION_DURATION)
        ) + fadeIn(
            initialAlpha = 0.3f,
            animationSpec = tween(AppConstants.EXPANSTION_TRANSITION_DURATION)
        )
    }
    val exitTransition = remember {
        shrinkVertically(
            // Expand from the top.
            shrinkTowards = Alignment.Top,
            animationSpec = tween(AppConstants.EXPANSTION_TRANSITION_DURATION)
        ) + fadeOut(
            // Fade in with the initial alpha of 0.3f.
            animationSpec = tween(AppConstants.EXPANSTION_TRANSITION_DURATION)
        )
    }
    AnimatedVisibility(
        visible = visible,
        initiallyVisible = initialVisibility,
        enter = enterTransition,
        exit = exitTransition
    ) {
//        Text(text = "dfffff")
        val height = games!!.size.times(60)

        LazyColumn(
            modifier = Modifier
                .height(height.dp)
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        ) {
            items(games!!) { game ->

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .clickable {
                        viewModel.selectedLeague=game
                       navController.navigate(BottomNavItem.countryLeagues.route)
                    }) {
                    AsyncImage(
                        modifier = Modifier
                            .size(40.dp)
                            .padding(start = 10.dp),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(game.games?.get(0)?.league?.logo.toString())
                            .build(),
                        contentDescription = "first team image",
                        placeholder = painterResource(id = R.drawable.app_icon),
                        error = painterResource(id = R.drawable.app_icon)
                    )
                    Text(
                        text = game.name.toString(),
                        color = Color.White,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(start = 10.dp, bottom = 5.dp)
                            .align(Alignment.CenterVertically),
                        lineHeight = 15.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}