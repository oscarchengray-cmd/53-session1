package edu.saihs.skills08.a53session1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.gson.Gson


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun maininsidepage(navController: NavHostController, page2id: Int){
        Column(modifier = Modifier
            .padding(horizontal = 30.dp)
            .padding(), horizontalAlignment = Alignment.Start) {
            var context = LocalContext.current
            val json = context.assets
                .open("媒體中心.json")
                .bufferedReader()
                .use {
                    it.readText()
                }
            val b: List<Media> = Gson().fromJson(json, Array<Media>::class.java).toList()
            val a= b[page2id]
            Text(text = a.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(5.dp))
            Row(modifier = Modifier
                .fillMaxWidth()
                , horizontalArrangement = Arrangement.SpaceBetween) {
                Row {
                    a.hall.forEach {
                        Text(
                            it,
                            color = if (it == "1館") Color(0xff11617f) else Color(0xff1AAB9F),
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.padding(5.dp))
                    }
                }
                Text("發文日期:"+a.dateTime)
            }
            Spacer(modifier = Modifier.padding(15.dp))
            Text(a.content)
        }
}

