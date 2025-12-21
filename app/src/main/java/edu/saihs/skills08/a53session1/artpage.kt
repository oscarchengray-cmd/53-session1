package edu.saihs.skills08.a53session1

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import java.io.IOException


data class floor1json(
    val title: String,
    val content: String,
    val image: String
)

data class floor2json(
    val title: String,
    val content: String,
    val image: String
)

@Composable
fun artpage() {
    var context = LocalContext.current
    val json1 = context.assets
        .open("公共藝術1館.json")
        .bufferedReader()
        .use {
            it.readText()
        }
    val json2 = context.assets
        .open("公共藝術2館.json")
        .bufferedReader()
        .use {
            it.readText()
        }
    val a: List<floor1json> = Gson().fromJson(json1, Array<floor1json>::class.java).toList()
    val b: List<floor2json> = Gson().fromJson(json2, Array<floor2json>::class.java).toList()
    var click by remember { mutableStateOf(0) }
    Column() {
        Row() {
            for (i in 1..2) {
                OutlinedButton(
                    onClick = { if (i == 1) click = 1 else click = 2 },
                    shape = RoundedCornerShape(0.dp),
                    modifier = Modifier.padding(horizontal = 10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (i == click) Color(0xff11617f) else Color.White
                    )
                ) {
                    Text(
                        if (i == 1) "一館" else "二館",
                        color = if (i == click) Color.White else Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        if (click == 1) {
            LazyColumn() {
                items(a) { json ->
                    Text(json.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    HorizontalDivider()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            json.content,
                            fontSize = 10.sp,
                            modifier = Modifier.weight(1f),
                            lineHeight = 15.sp
                        )
                        val folderPath = "公共藝術/"
                        val bitmap = remember(json.image.trim()) {
                            val fullPath = folderPath + json.image.trim()
                            context.assets.open(fullPath).use { stream ->
                                BitmapFactory.decodeStream(stream).asImageBitmap()
                            }
                        }
                        Image(
                            bitmap = bitmap,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(200.dp),
                        )
                    }
                }
            }

        } else if (click == 2) {
            LazyColumn() {
                items(b) { json ->
                    Text(json.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    HorizontalDivider()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            json.content,
                            fontSize = 12.sp,
                            modifier = Modifier.weight(1f),
                            lineHeight = 15.sp
                        )
                        val folderPath = "公共藝術/"
                        val bitmap = remember(json.image.trim()) {
                            val fullPath = folderPath + json.image.trim()
                            context.assets.open(fullPath).use { stream ->
                                BitmapFactory.decodeStream(stream).asImageBitmap()
                            }
                        }
                        Image(
                            bitmap = bitmap,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(200.dp),
                        )
                    }
                }
            }
        } else {

        }
    }
}