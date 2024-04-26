package com.example.livebasketballcompose.composescreens

import android.text.format.DateFormat
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.livebasketballcompose.R
import com.example.livebasketballcompose.models.Game
import com.example.livebasketballcompose.ui.theme.ColorPrimary
import com.example.livebasketballcompose.ui.theme.ColorPrimaryDark
import com.example.livebasketballcompose.utils.CodeUtils.dateAndTime
import com.example.livebasketballcompose.viewmodels.BasketScoreViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Composable
fun showMatchDescription() {
    val viewModel: BasketScoreViewModel = viewModel(LocalContext.current as ComponentActivity)
    if (viewModel.selectedGame!=null)
    {
        buildFunctionForMatch(viewModel.selectedGame!!)
    }


}

@Composable
fun buildFunctionForMatch(selectedGame: Game) {
    val gameDate = dateAndTime(selectedGame.date)
  

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

                            },
                        tint = Color.White
                    )
                    Text(
                        text = "Match Info",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .weight(1f)
                            .padding(
                                top = 10.dp
                            ),
                        textAlign = TextAlign.Center
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp)
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
                                .data(selectedGame?.teams?.away?.logo.toString())
                                .build(),
                            contentDescription = "first team image",
                            placeholder = painterResource(id = R.drawable.app_icon),
                            error = painterResource(id = R.drawable.app_icon)
                        )
                        Text(
                            text = selectedGame.teams?.away?.name.toString(),
                            color = Color.White,
                            fontSize = 16.sp,
                            lineHeight = 15.sp,
                            modifier = Modifier
                                .padding(start = 0.dp, bottom = 0.dp),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = selectedGame.status?.longName.toString(),
                            color = Color.White,
                            modifier = Modifier.padding(top = 10.dp),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = if (!selectedGame.status?.short.equals("NS", true))
                                "" + selectedGame.scores?.home?.total + "  VS  " + selectedGame.scores?.away?.total else "VS",
                            color = Color.White,
                            modifier = Modifier.padding(top = 0.dp),
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = gameDate.toString(),
                            color = Color.White,
                            modifier = Modifier.padding(top = 0.dp),
                            fontSize = 14.sp,
                            lineHeight = 15.sp,
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
                                .data(selectedGame?.teams?.home?.logo.toString())
                                .build(),
                            contentDescription = "second team image",
                            placeholder = painterResource(id = R.drawable.app_icon),
                            error = painterResource(id = R.drawable.app_icon)
                        )
                        Text(
                            text = selectedGame?.teams?.home?.name.toString(),
                            color = Color.White,
                            fontSize = 16.sp,
                            lineHeight = 15.sp,
                            modifier = Modifier
                                .padding(start = 0.dp, bottom = 0.dp),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
        if (!selectedGame.status?.short.equals("NS", true))
        {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 10.dp),
                colors = CardDefaults.cardColors(containerColor = ColorPrimary)
            ) {
                Text(
                    text = "Score board",
                    color = Color.White,
                    modifier = Modifier.padding(top = 10.dp, bottom = 20.dp, start = 20.dp)
                )
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp, start = 5.dp, end = 5.dp)) {
                    Column (modifier = Modifier.fillMaxWidth()){
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)) {
                            Text(text = "", modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f))
                            Text(text = "Q1",modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f), textAlign = TextAlign.Center)
                            Text(text = "Q2",modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),textAlign = TextAlign.Center)
                            Text(text = "Q3",modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),textAlign = TextAlign.Center)
                            Text(text = "Q4",modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),textAlign = TextAlign.Center)
                            Text(text = "Score",modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),textAlign = TextAlign.Center)
                        }
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)) {
                            Text(text = selectedGame?.teams?.away?.name.toString()?:"", modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f)
                                .padding(start = 5.dp))
                            Text(text = selectedGame?.scores?.away?.quarter1?.toString()?:"",modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),textAlign = TextAlign.Center)
                            Text(text = selectedGame?.scores?.away?.quarter2?.toString()?:"",modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),textAlign = TextAlign.Center)
                            Text(text = selectedGame?.scores?.away?.quarter3?.toString()?:"",modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),textAlign = TextAlign.Center)
                            Text(text = selectedGame?.scores?.away?.quarter4?.toString()?:"",modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),textAlign = TextAlign.Center)
                            Text(text = selectedGame?.scores?.away?.total?.toString()?:"",modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),textAlign = TextAlign.Center)
                        }
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, bottom = 10.dp)) {
                            Text(text = selectedGame?.teams?.home?.name.toString()?:"", modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f)
                                .padding(start = 5.dp))
                            Text(text = selectedGame?.scores?.home?.quarter1?.toString()?:"",modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),textAlign = TextAlign.Center)
                            Text(text = selectedGame?.scores?.home?.quarter2?.toString()?:"",modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),textAlign = TextAlign.Center)
                            Text(text = selectedGame?.scores?.home?.quarter3?.toString()?:"",modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),textAlign = TextAlign.Center)
                            Text(text = selectedGame?.scores?.home?.quarter4?.toString()?:"",modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),textAlign = TextAlign.Center)
                            Text(text = selectedGame?.scores?.home?.total?.toString()?:"",modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),textAlign = TextAlign.Center)
                        }
                    }

                }

            }
        }
        else
        {
            Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                Text(text = "Match Not Started Yet", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

        } 
       
    }
}
