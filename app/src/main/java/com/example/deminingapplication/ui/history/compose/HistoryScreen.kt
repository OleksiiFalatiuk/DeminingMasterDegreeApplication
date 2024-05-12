package com.example.deminingapplication.ui.history.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.locale.entity.DeminingEntity
import com.example.deminingapplication.R

@Composable
fun HistoryScreen(
    deminingData: SnapshotStateList<DeminingEntity>? = null
) {
    val items = remember { deminingData }
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
                text = "Історія ваших звернень",
                color = colorResource(id = R.color.text),
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.sf_pro_display_semibold)),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items?.let {
                    items(it) { item ->
                        HistoryItem(item)
                    }
                }
            }
        }
    }
}

@Composable
fun HistoryItem(deminingEntity: DeminingEntity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Text(
            text = "Геолокація користувача: ${deminingEntity.userGeo}",
            fontSize = 16.sp,
            color = colorResource(id = R.color.text),
            fontFamily = FontFamily(Font(R.font.sf_pro_display_medium))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Дата створення звернення: ${deminingEntity.currentDate}",
            fontSize = 16.sp,
            color = colorResource(id = R.color.text),
            fontFamily = FontFamily(Font(R.font.sf_pro_display_medium))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Рівень небезпеки: ${deminingEntity.warningLevel}",
            fontSize = 16.sp,
            color = colorResource(id = checkUserWarning(deminingEntity.warningLevel)),
            fontFamily = FontFamily(Font(R.font.sf_pro_display_medium))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Опис звернення: ${deminingEntity.deminingDescription}",
            fontSize = 16.sp,
            color = colorResource(id = R.color.text),
            fontFamily = FontFamily(Font(R.font.sf_pro_display_medium))
        )
        Text(
            text = "Статус звернення: ${getStatusOfAppeal()}",
            fontSize = 16.sp,
            color = colorResource(id = R.color.text),
            fontFamily = FontFamily(Font(R.font.sf_pro_display_medium))
        )
        Spacer(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.darkText)))
    }
}

private fun checkUserWarning(warningLevel: String): Int {
    return when (warningLevel) {
        "Низький" -> R.color.green
        "Середній" -> R.color.yellow
        "Високий" -> R.color.colorError
        else -> R.color.colorError
    }
}

private fun getStatusOfAppeal(): String{
    return when((1..3).random()){
        1 -> "Прийнято"
        2 -> "Переглянуто"
        3 -> "Розміновано"
        else -> "Прийнято"
    }
}

@Preview
@Composable
fun PreviewHistoryScreen() {
    HistoryScreen()
}

@Preview
@Composable
fun PreviewHHistoryItem() {
    HistoryItem(
        deminingEntity = DeminingEntity(
            firebaseAuthToken = "",
            userGeo = "12312321312",
            deminingDescription = "SUS",
            currentDate = "06.06.2026",
            warningLevel = "Високий",
            appealId = ""
        )
    )
}