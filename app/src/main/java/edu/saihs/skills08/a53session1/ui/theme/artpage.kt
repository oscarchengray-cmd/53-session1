package edu.saihs.skills08.a53session1.ui.theme

import android.media.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.gson.Gson


data class floor1json(
    val title: String,
    val content: String,
    val image: Image,
)
data class floor2json(
    val title: String,
    val content: String,
    val image: Image,
)
@Composable
fun artpage(){
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
}