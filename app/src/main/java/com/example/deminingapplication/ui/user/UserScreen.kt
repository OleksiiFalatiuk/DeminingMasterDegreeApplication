package com.example.deminingapplication.ui.user

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.data.locale.entity.UserEntity
import com.example.deminingapplication.R

@Composable
fun UserScreen(
    userEntity: UserEntity? = null,
    onButtonClick:() -> Unit
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
            Text(
                text = "Ім'я користувача ${userEntity?.userName}",
                fontSize = 14.sp,
                color = colorResource(id = R.color.text),
                fontFamily = FontFamily(Font(R.font.sf_pro_display_medium))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Прізвище користувача ${userEntity?.userSurname}",
                fontSize = 14.sp,
                color = colorResource(id = R.color.text),
                fontFamily = FontFamily(Font(R.font.sf_pro_display_medium))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Місто користувача ${userEntity?.userCity}",
                fontSize = 14.sp,
                color = colorResource(id = R.color.text),
                fontFamily = FontFamily(Font(R.font.sf_pro_display_medium))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Телефон користувача ${userEntity?.userPhoneNumber}",
                fontSize = 14.sp,
                color = colorResource(id = R.color.text),
                fontFamily = FontFamily(Font(R.font.sf_pro_display_medium))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Вік користувача ${userEntity?.userAge}",
                fontSize = 14.sp,
                color = colorResource(id = R.color.text),
                fontFamily = FontFamily(Font(R.font.sf_pro_display_medium))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Пошта користувача ${userEntity?.userEmail}",
                fontSize = 14.sp,
                color = colorResource(id = R.color.text),
                fontFamily = FontFamily(Font(R.font.sf_pro_display_medium))
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.Center
            ) {
                Card(
                    Modifier
                        .wrapContentSize()
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {

                        },
                    backgroundColor = colorResource(id = R.color.accent),
                    elevation = 0.dp
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(vertical = 16.dp, horizontal = 48.dp)
                            .clickable {
                                onButtonClick()
                            },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Вийти",
                            color = colorResource(id = R.color.primary),
                            fontFamily = FontFamily(Font(R.font.sf_pro_display_regular)),
                            fontSize = 16.sp,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewUserScreen() {
    UserScreen(){

    }
}