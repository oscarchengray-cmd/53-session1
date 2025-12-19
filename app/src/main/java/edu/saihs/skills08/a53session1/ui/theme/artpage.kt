package edu.saihs.skills08.a53session1.ui.theme

import android.graphics.BitmapFactory
import android.media.Image
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import edu.saihs.skills08.a53session1.R
import org.jetbrains.annotations.Async
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
            for (i in 1..2)
                OutlinedButton(
                    onClick = { if (i == 1) click = 1 else click = 2 },
                    shape = RoundedCornerShape(0.dp),
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(
                        if (i == 1) "一館" else "二館",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )

                }
        }
        if (click == 1) {
            a.forEachIndexed { index, json ->
                Column(Modifier.padding(10.dp)) {
                    Log.d("",json.image)
                    Text(json.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    HorizontalDivider()
                    Text(json.content)
                    // 在 Logcat 印出 assets 裡的所有檔案
                    try {
                        val assetManager = context.assets
                        val files = assetManager.list("") // 列出根目錄所有檔案
                        files?.forEach { filename ->
                            Log.d("AssetsCheck", "找到檔案: $filename")
                        }
                    } catch (e: IOException) {
                        Log.e("AssetsCheck", "讀取失敗")
                    }

                    val bitmap = remember(json.image.trim()) {
                        val open = context.assets.open(json.image.trim())
                        BitmapFactory.decodeStream(open).asImageBitmap()
                    }
                    Image(
                        bitmap = bitmap,
                        null
                    )
                }


            }
        } else if (click == 2) {

        } else {

        }
    }
}