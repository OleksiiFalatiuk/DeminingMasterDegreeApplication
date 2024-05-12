package com.example.deminingapplication.ui.appeal.compose

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deminingapplication.R
import com.example.deminingapplication.ui.main.MainViewModel

@Composable
fun AppealScreen(
    mainViewModel: MainViewModel? = null,
    onButtonClick:(deminingDescription: String, warningLevel: String) -> Unit
) {
    val kinds = listOf("Низький", "Середній", "Високий")
    val (selected, setSelected) = remember { mutableStateOf("Низький") }
    val deminingDescription = remember {
        mutableStateOf("")
    }
    val warningLevel = remember {
        mutableStateOf("Низький")
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.primary)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Будь ласка, опишіть своє зверненння про заміновану територію",
                color = colorResource(id = R.color.text),
                fontFamily = FontFamily(Font(R.font.sf_pro_display_semibold)),
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            UserDataItem("Опис звернення", deminingDescription)
            Spacer(modifier = Modifier.height(16.dp))
            KindRadioGroup(
                mItems = kinds,
                selected,
                setSelected,
                mainViewModel,
                warningLevel
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
                            onButtonClick(deminingDescription.value, warningLevel.value)
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
fun UserDataItem(text: String, state: MutableState<String>?) {
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
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            value = state?.value ?: "",
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = lightBlue,
                cursorColor = Color.Black,
                disabledLabelColor = lightBlue,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                state?.value = it
            },
            shape = RoundedCornerShape(8.dp),
        )
    }
}

@Composable
fun KindRadioGroup(
    mItems: List<String>,
    selected: String,
    setSelected: (selected: String) -> Unit,
    mainViewModel: MainViewModel?,
    warningLevel: MutableState<String>
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Будь ласка оберіть рівент небезпеки",
                color = colorResource(id = R.color.text),
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.sf_pro_display_medium))
            )
            mItems.forEach { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selected == item,
                        onClick = {
                            warningLevel.value = item
                            setSelected(item)
                        },
                        enabled = true,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color(0xff76a9ff)
                        )
                    )
                    Text(
                        text = item, modifier = Modifier
                            .width(65.dp)
                            .padding(start = 4.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewAppealScreen() {
    AppealScreen(
        onButtonClick = { i, a ->

        }
    )
}