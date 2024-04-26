package com.example.livebasketballcompose.composescreens

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.livebasketballcompose.BuildConfig
import com.example.livebasketballcompose.R
import com.example.livebasketballcompose.ui.theme.ColorPrimary
import com.example.livebasketballcompose.ui.theme.ColorPrimaryDark

@Composable
fun moreScreen(current: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = ColorPrimaryDark)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            colors = CardDefaults.cardColors(containerColor = ColorPrimary),
            shape = RoundedCornerShape(
                topStart = 0.dp, topEnd = 0.dp, bottomStart = 20.dp, bottomEnd = 20.dp
            )
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "More", fontSize = 18.sp, color = Color.White)
            }
        }

        Card(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                rateUsFunction(current)
            }
            .padding(top = 40.dp, start = 10.dp, end = 10.dp)
            .height(50.dp),
            colors = CardDefaults.cardColors(containerColor = ColorPrimary)) {
            Row(
                verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    modifier = Modifier
                        .height(30.dp)
                        .padding(start = 10.dp),
                    painter = painterResource(id = R.drawable.rate_the_app),
                    contentDescription = "rate app"
                )
                Text(
                    text = "Rate us",
                    modifier = Modifier
                        .weight(1f, fill = true)
                        .padding(start = 10.dp),
                    color = Color.White,
                    fontSize = 18.sp
                )
                Icon(
                    painter = painterResource(id = R.drawable.forward),
                    contentDescription = "forward icon",
                    modifier = Modifier.padding(end = 10.dp),
                    tint = Color.White
                )
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 10.dp, end = 10.dp)
                .height(50.dp)
                .clickable {
                    feedBack(current)
                },
            colors = CardDefaults.cardColors(containerColor = ColorPrimary)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.feedback),
                    contentDescription = "contact us",
                    modifier = Modifier
                        .height(30.dp)
                        .padding(start = 10.dp),
                    tint = Color.White
                )
                Text(
                    text = "Feedback",
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp),
                    fontSize = 18.sp,
                    color = Color.White
                )
                Icon(
                    painter = painterResource(id = R.drawable.forward),
                    contentDescription = "forward icon",
                    tint = Color.White,
                    modifier = Modifier.padding(end = 10.dp)
                )
            }


        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 10.dp, end = 10.dp)
                .height(50.dp), colors = CardDefaults.cardColors(containerColor = ColorPrimary)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.notification1),
                    contentDescription = "contact us",
                    modifier = Modifier
                        .height(30.dp)
                        .padding(start = 10.dp),
                    tint = Color.White
                )
                Text(
                    text = "Notifications",
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp),
                    fontSize = 18.sp,
                    color = Color.White
                )
                Switch(checked = false, onCheckedChange = {})
            }


        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 10.dp, end = 10.dp)
                .height(50.dp)
                .clickable {
                    shareUsFunction(current)
                }, colors = CardDefaults.cardColors(containerColor = ColorPrimary)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.share),
                    contentDescription = "contact us",
                    modifier = Modifier
                        .height(30.dp)
                        .padding(start = 10.dp),
                    tint = Color.White
                )
                Text(
                    text = "Share App",
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp),
                    fontSize = 18.sp,
                    color = Color.White
                )
                Icon(
                    painter = painterResource(id = R.drawable.forward),
                    contentDescription = "forward icon",
                    tint = Color.White,
                    modifier = Modifier.padding(end = 10.dp)
                )
            }


        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 10.dp, end = 10.dp)
                .height(50.dp)
                .clickable {
                    privacyFunction(current)
                }, colors = CardDefaults.cardColors(containerColor = ColorPrimary)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.privacy_policy),
                    contentDescription = "privacy policy",
                    modifier = Modifier
                        .height(30.dp)
                        .padding(start = 10.dp),
                    tint = Color.White
                )
                Text(
                    text = "Privacy Policy",
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp),
                    fontSize = 18.sp,
                    color = Color.White
                )
                Icon(
                    painter = painterResource(id = R.drawable.forward),
                    contentDescription = "forward icon",
                    tint = Color.White,
                    modifier = Modifier.padding(end = 10.dp)
                )
            }


        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 10.dp, end = 10.dp)
                .height(50.dp)
                .clickable {
                    termsAndCondition(current)
                }, colors = CardDefaults.cardColors(containerColor = ColorPrimary)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.terms_and_conditions),
                    contentDescription = "terms and conditions",
                    modifier = Modifier
                        .height(30.dp)
                        .padding(start = 10.dp),
                    tint = Color.White
                )
                Text(
                    text = "Terms of Use",
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 10.dp),
                    fontSize = 18.sp,
                    color = Color.White
                )
                Icon(
                    painter = painterResource(id = R.drawable.forward),
                    contentDescription = "forward icon",
                    tint = Color.White,
                    modifier = Modifier.padding(end = 10.dp)
                )
            }


        }
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Version: " + BuildConfig.VERSION_NAME,
            color = Color.White,
            modifier = Modifier
                .padding(bottom = 80.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

private fun rateUsFunction(current: Context) {
    try {
        current.startActivity(
            Intent(
                Intent.ACTION_VIEW, Uri.parse("market://details?id=" + current?.packageName)
            )
        )
    } catch (e: ActivityNotFoundException) {

        try {
            current.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + current?.packageName)
                )
            )
        } catch (e: ActivityNotFoundException) {
            Log.d("Exception", "" + e.message)
        }

    }
}

private fun feedBack(current: Context) {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.data = Uri.parse("mailto:") // only email apps should handle this
    intent.putExtra(Intent.EXTRA_EMAIL, Array(1) { "mailto:android@sportsstream.org" })
    intent.putExtra(
        Intent.EXTRA_SUBJECT, current.resources.getString(R.string.app_name)
    )
    current.startActivity(Intent.createChooser(intent, "Send Email..."))
}

private fun shareUsFunction(current: Context) {
    val intent = Intent()
    intent.action = Intent.ACTION_SEND
    intent.putExtra(
        Intent.EXTRA_TEXT, "Please download this app for live  streaming.\n" +
                "https://play.google.com/store/apps/details?id=" + current?.packageName
    )
    intent.type = "text/plain"
    current.startActivity(intent)
}

private fun privacyFunction(current: Context) {
    try {
        val url = "http://sportsstream.org/#privacy"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        current.startActivity(i)
    } catch (e: ActivityNotFoundException) {
    }
}

private fun termsAndCondition(current: Context) {
    try {
        val url = "http://sportsstream.org/#privacy"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        current.startActivity(i)
    } catch (e: ActivityNotFoundException) {

    }
}