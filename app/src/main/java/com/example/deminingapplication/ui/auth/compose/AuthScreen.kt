package com.example.deminingapplication.ui.auth.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deminingapplication.R

@Composable
fun AuthScreen(
    onButtonClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primary)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            Modifier
                .wrapContentSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = "Будь ласка авторизуйтусь у застосунок.",
                fontFamily = FontFamily(Font(R.font.sf_pro_display_semibold)),
                fontSize = 16.sp,
                color = colorResource(id = R.color.text)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                Modifier
                    .wrapContentSize()
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
                        text = "Google авторизація",
                        color = colorResource(id = R.color.primary),
                        fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                        fontSize = 16.sp,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSplashScreen() {
    AuthScreen(onButtonClick = {

    })
}