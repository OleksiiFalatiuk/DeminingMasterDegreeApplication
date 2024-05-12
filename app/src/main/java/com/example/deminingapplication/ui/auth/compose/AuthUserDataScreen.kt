package com.example.deminingapplication.ui.auth.compose

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.locale.entity.UserEntity
import com.example.deminingapplication.R
import com.example.deminingapplication.ui.auth.AuthViewModel

@Composable
fun AuthUserDataScreen(
    authViewModel: AuthViewModel? = null,
    onButtonClick: (UserEntity) -> Unit
) {
    val userName = remember {
        mutableStateOf("")
    }
    val userSurname = remember {
        mutableStateOf("")
    }
    val userCity = remember {
        mutableStateOf("")
    }
    val userPhoneNumber = remember {
        mutableStateOf("")
    }
    val userAge = remember {
        mutableStateOf("")
    }
    val userEmail = remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primary))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
                .verticalScroll(rememberScrollState())
        ) {
            UserDataItem("Ім'я", userName)
            Spacer(modifier = Modifier.height(16.dp))
            UserDataItem("Прізвище", userSurname)
            Spacer(modifier = Modifier.height(16.dp))
            UserDataItem("Місто", userCity)
            Spacer(modifier = Modifier.height(16.dp))
            UserDataItem("Вік громадянина", userAge)
            Spacer(modifier = Modifier.height(16.dp))
            UserDataItem("Електронна пошта", userEmail)
            Spacer(modifier = Modifier.height(16.dp))
            UserDataItem("Номер телефону", userPhoneNumber)
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
                            .padding(16.dp)
                            .clickable {
                                onButtonClick(
                                    UserEntity(
                                        userName = userName.value,
                                        userSurname = userSurname.value,
                                        userCity = userCity.value,
                                        userPhoneNumber = userPhoneNumber.value,
                                        userAge = userAge.value,
                                        userEmail = userEmail.value,
                                    )
                                )
                            },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Зберегти інформацію",
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

@Composable
fun UserDataItem(text: String, innerState: MutableState<String>) {
    val lightBlue = Color(0xffd8e6ff)
    val blue = Color(0xff76a9ff)
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            textAlign = TextAlign.Start,
            color = blue
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = innerState.value,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = lightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                innerState.value = it
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
        )
    }
}

@Preview
@Composable
fun PreviewAuthUserDataScreen() {
    AuthUserDataScreen() {

    }
}