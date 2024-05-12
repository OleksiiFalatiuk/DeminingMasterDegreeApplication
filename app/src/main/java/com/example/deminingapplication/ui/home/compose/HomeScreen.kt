package com.example.deminingapplication.ui.home.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deminingapplication.R
import com.example.deminingapplication.utils.HomeEvent

@Composable
fun HomeScreen(
    onButtonClick: (HomeEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primary)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            SimpleButton(text = "Написати звернення") {
                onButtonClick(HomeEvent.WriteAnAppeal)
            }
            Spacer(modifier = Modifier.height(16.dp))
            SimpleButton(text = "Історія звернень") {
                onButtonClick(HomeEvent.RequestHistory)
            }
            Spacer(modifier = Modifier.height(16.dp))
            SimpleButton(text = "Інформація про користувача") {
                onButtonClick(HomeEvent.UserInfo)
            }
        }
    }
}

@Composable
fun SimpleButton(
    text: String,
    onButtonClick:() -> Unit
){
    Card(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onButtonClick()
            },
        backgroundColor = colorResource(id = R.color.accent),
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                color = colorResource(id = R.color.primary),
                fontFamily = FontFamily(Font(R.font.sf_pro_display_medium)),
                fontSize = 16.sp,
            )
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen() {

    }
}