package com.example.livebasketballcompose.composescreens

import android.text.format.DateFormat
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.offset
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
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.livebasketballcompose.R
import com.example.livebasketballcompose.models.Groups
import com.example.livebasketballcompose.models.StandingModel
import com.example.livebasketballcompose.models.StandingsModel
import com.example.livebasketballcompose.models.Team
import com.example.livebasketballcompose.navigation.BottomNavItem
import com.example.livebasketballcompose.networklayer.ApiState
import com.example.livebasketballcompose.ui.theme.ColorPrimary
import com.example.livebasketballcompose.ui.theme.ColorPrimaryDark
import com.example.livebasketballcompose.utils.CodeUtils
import com.example.livebasketballcompose.viewmodels.BasketScoreViewModel
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Composable
fun CountryWithLeagues(navController: NavHostController) {
    val viewModel: BasketScoreViewModel = viewModel(LocalContext.current as ComponentActivity)
    // Pass any value as the keys
    LaunchedEffect(key1 = true) {
        if (viewModel.selectedLeague != null) {
            viewModel.selectedLeague!!.id?.let {
                viewModel.getLeagueDetails(
                    it,
                    viewModel.selectedLeague!!.season
                )
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = ColorPrimaryDark)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Image(
                painter = painterResource(id = R.mipmap.header_image),
                contentDescription = "header Image",
                modifier = Modifier
                    .offset(-1.dp, -10.dp)
                    .fillMaxWidth()
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        painter = painterResource(id = R.drawable.back_latest),
                        contentDescription = "back button",
                        modifier = Modifier
                            .padding(top = 10.dp, start = 20.dp)
                            .clickable {
                                navController?.popBackStack()
                            },
                        tint = Color.White
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(60.dp),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(viewModel.selectedLeague?.games?.get(0)?.league?.logo)
                            .build(),
                        contentDescription = "first team image",
                        placeholder = painterResource(id = R.drawable.app_icon),
                        error = painterResource(id = R.drawable.app_icon)
                    )
                    Text(
                        text = viewModel.selectedLeague?.name.toString(),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }


            }
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            TabScreen(navController)
        }
    }
}

@Composable
fun TabScreen(navController: NavHostController) {
    val viewModel: BasketScoreViewModel = viewModel(LocalContext.current as ComponentActivity)
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
            var tabIndex by remember { mutableStateOf(0) }
            val leagueStandingList by viewModel.leagueStandingList.collectAsStateWithLifecycle()


            val tabs = listOf("Matches", "Teams", "Standings")

            Column(modifier = Modifier.fillMaxWidth()) {
                TabRow(
                    selectedTabIndex = tabIndex,
                    containerColor = Color.Transparent,
                    indicator = {

                    },
                    divider = {

                    },
                    modifier = Modifier.padding(1.dp)
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(text = {
                            Text(
                                title,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        },

                            modifier = if (tabIndex == index) Modifier
                                .shadow(elevation = 10.dp)
                                .clip(shape = RoundedCornerShape(50.dp))
                                .background(color = ColorPrimary) else Modifier
                                .shadow(elevation = 10.dp, shape = RoundedCornerShape(50.dp))
                                .background(color = ColorPrimaryDark),
                            selected = tabIndex == index,
                            onClick = { tabIndex = index }
                        )
                    }
                }
                when (tabIndex) {
                    0 -> MatchScreen(viewModel,navController)
                    1 -> TeamsScreen(leagueStandingList)
                    2 -> StandingsScreen(leagueStandingList)
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
        CircularProgressIndicator()
    }
}

@Composable
fun StandingsScreen(leagueStandingList: StandingsModel?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = ColorPrimary)
    ) {
        if (leagueStandingList != null) {
            if (!leagueStandingList.groups.isNullOrEmpty()) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = ColorPrimary)
                ) {
                    LazyColumn(contentPadding = PaddingValues(
                        bottom = 60.dp
                    )) {
                        items(leagueStandingList.groups) { group ->
                            showGroupNameWithStandings(group)
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun TeamsScreen(leagueStandingList: StandingsModel?) {
    if (leagueStandingList != null) {
        if (!leagueStandingList.groups.isNullOrEmpty()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = ColorPrimary)
            ) {
                LazyColumn(contentPadding = PaddingValues(
                    bottom = 60.dp
                ) ) {
                    items(leagueStandingList.groups) { group ->
                        showGroupName(group)
                    }
                }
            }
        }

    }

}
@Composable
fun showGroupNameWithStandings(group: Groups) {

    var diff = 0
    val standingList: MutableList<StandingModel> =
        ArrayList()
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(text = group.name.toString(), color = Color.White, modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
    if (!group.standings.isNullOrEmpty()) {
        group.standings.forEach { standing ->
            if (standing.games?.played != null) {
                if (standing.games?.played!! > 0) {

                    diff = standing.games?.played!! - standing.games?.win?.total!!

                    standingList.add(
                        StandingModel(
                            standing.position,
                            standing.team?.logo,
                            standing.team?.name,
                            standing.games?.played,
                            standing.games?.win?.total,
                            standing.games?.lose?.total,
                            diff,
                            standing.games?.win?.percentage
                        )
                    )
                }
            }
        }
    }
    showGroupTeamStandings(standingList)

}

@Composable
fun showGroupTeamStandings(standingList: MutableList<StandingModel>) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)) {
            Text(text = "#"
               , color = Color.White, modifier = Modifier.padding(end = 10.dp, start = 10.dp))
            Text(text = "Teams",modifier = Modifier
                .fillMaxWidth()
                .weight(3f),  color = Color.White)
            Text(text = "P",modifier = Modifier
                .fillMaxWidth()
                .weight(1f),textAlign = TextAlign.Center, color = Color.White)
            Text(text = "W",modifier = Modifier
                .fillMaxWidth()
                .weight(1f),textAlign = TextAlign.Center, color = Color.White)
            Text(text = "L",modifier = Modifier
                .fillMaxWidth()
                .weight(1f),textAlign = TextAlign.Center, color = Color.White)
            Text(text = "DIFF",modifier = Modifier
                .fillMaxWidth()
                .weight(1f),textAlign = TextAlign.Center, color = Color.White)
            Text(text = "PCT",modifier = Modifier
                .fillMaxWidth()
                .weight(1f),textAlign = TextAlign.Center, color = Color.White)
        }
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .height(standingList.size * 40.dp)){
            items(standingList){
                standingItem->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)) {
                    Text(text = standingItem.teamPosition.toString()
                        , color = Color.White,modifier =  Modifier.padding(end = 10.dp, start = 10.dp))
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .weight(3f)) {
                        AsyncImage(
                            modifier = Modifier
                                .size(30.dp)
                                .padding(start = 10.dp),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(standingItem.teamLogo.toString())
                                .build(),
                            contentDescription = "first team image",
                            placeholder = painterResource(id = R.drawable.app_icon),
                            error = painterResource(id = R.drawable.app_icon))
                        Text(text = standingItem.teamName.toString(), color = Color.White)
                    }

                    Text(text = standingItem.teamPoints.toString(),modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),textAlign = TextAlign.Center, color = Color.White)
                    Text(text = standingItem.teamWin.toString(),modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),textAlign = TextAlign.Center, color = Color.White)
                    Text(text = standingItem.teamLose.toString(),modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),textAlign = TextAlign.Center, color = Color.White)
                    Text(text = standingItem.teamDiff.toString(),modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),textAlign = TextAlign.Center, color = Color.White)
                    Text(text = standingItem.teamPct.toString(),modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),textAlign = TextAlign.Center, color = Color.White)
                }
            }
        }
    }

}

@Composable
fun showGroupName(group: Groups) {
    var liveTeams: MutableList<Team> =
        ArrayList<Team>()

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(text = group.name.toString(), color = Color.White, modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
    if (!group.standings.isNullOrEmpty()) {
        group.standings.forEach { standing ->
            standing.team?.let { liveTeams.add(it) }
        }
    }
    showTeams(liveTeams)
}

@Composable
fun showTeams(liveTeams: MutableList<Team>) {
    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .height(liveTeams.size * 50.dp)) {
        items(liveTeams) { team->
           Row (modifier = Modifier.fillMaxWidth()){
               AsyncImage(
                   modifier = Modifier
                       .size(50.dp)
                       .padding(start = 10.dp, top = 5.dp, bottom = 5.dp),
                   model = ImageRequest.Builder(LocalContext.current)
                       .data(team.logo.toString())
                       .build(),
                   contentDescription = "first team image",
                   placeholder = painterResource(id = R.drawable.app_icon),
                   error = painterResource(id = R.drawable.app_icon))
               Text(
                   text = team.name.toString(),
                   color = Color.White,
                   fontSize = 14.sp,
                   modifier = Modifier
                       .padding(start = 10.dp)
                       .align(Alignment.CenterVertically),
                   lineHeight = 15.sp,
                   textAlign = TextAlign.Center
               )
           }
        }

    }
}

@Composable
fun MatchScreen(viewModel: BasketScoreViewModel, navController: NavHostController) {
    if (viewModel.selectedLeague!=null){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = ColorPrimary)
        ) {
            if (!viewModel.selectedLeague?.games.isNullOrEmpty()){
                LazyColumn(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(
                        top = 10.dp,
                        bottom = 60.dp
                    )
                ) {
                    items(viewModel.selectedLeague?.games!!) { game ->
                        val gameDate = CodeUtils.dateAndTime(game.date)
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
                                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(10.dp),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)) {
                                    Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                                        AsyncImage(
                                            modifier = Modifier
                                                .size(20.dp)
                                                .padding(top = 2.dp),
                                            model = ImageRequest.Builder(LocalContext.current)
                                                .data(viewModel.selectedLeague!!.games?.get(0)?.league?.logo)
                                                .build(),
                                            contentDescription = "league logo",
                                            placeholder = painterResource(id = R.drawable.app_icon),
                                            error = painterResource(id = R.drawable.app_icon)
                                        )
                                        Text(text = viewModel.selectedLeague!!
                                            .name.toString())
                                    }
                                }

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
    }

}

