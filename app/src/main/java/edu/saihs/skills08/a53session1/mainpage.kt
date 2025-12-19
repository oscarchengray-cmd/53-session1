package edu.saihs.skills08.a53session1

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.gson.Gson

data class Media(
    val title: String,
    val dateTime: String,
    val hall: List<String>,
    val content: String
)


@Composable
fun mainpage(navController: NavHostController) {
    var context = LocalContext.current
    val json = context.assets
            .open("媒體中心.json")
            .bufferedReader()
            .use {
                it.readText()
            }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        Arrangement.Top
    ) {
        val pagerState = rememberPagerState(pageCount = { 2 })
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            Column() {
                when (page) {
                    0 -> {
                        Image(
                            painter = painterResource(id = R.drawable.pager0),
                            contentDescription = null,
                            modifier = Modifier
                                .height(250.dp)
                                .fillMaxWidth(),
                            contentScale = ContentScale.Crop,
                        )
                    }

                    1 -> Image(
                        painter = painterResource(id = R.drawable.pager1),
                        contentDescription = null,
                        modifier = Modifier
                            .height(250.dp)
                            .fillMaxWidth(),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(), Arrangement.End
        ) {
            for (i in 1..2) {
                val animatedColor by animateColorAsState(
                    if (pagerState.currentPage == i - 1) Color.DarkGray else Color.Gray,
                    animationSpec = tween(
                        durationMillis = 1000
                    )
                )
                Box(
                    modifier = Modifier
                        .background(
                            color = animatedColor,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .animateContentSize()
                        .size(
                            if (pagerState.currentPage == i - 1) 20.dp else 10.dp,
                            10.dp
                        )
                )
                Spacer(modifier = Modifier.padding(5.dp))
            }
        }
        Column() {
            val a: List<Media> = Gson().fromJson(json, Array<Media>::class.java).toList()
            LazyColumn() {
                item { Text("媒體中心", modifier = Modifier.padding(10.dp)) }
                itemsIndexed(a) { index, item ->
                    Row(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .clickable(onClick = { navController.navigate("main inside/$index") })
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 10.dp)
                                .width(100.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = item.dateTime, color = Color.Gray)
                            Spacer(modifier = Modifier.padding(3.dp))
                            Row {
                                item.hall.forEach { it ->
                                    Text(
                                        text = it,
                                        color = if (it == "1館") Color(0xff11617f) else Color(
                                            0xff1AAB9F
                                        )
                                    )
                                    Spacer(modifier = Modifier.padding(3.dp))
                                }
                            }

                        }
                        Text(item.title, fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                    }
                    HorizontalDivider(thickness = 1.dp)
                }
                item { Text("購票中心", modifier = Modifier.padding(10.dp)) }
                item {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .border(
                                width = 2.dp,
                                color = Color.DarkGray,
                                shape = RoundedCornerShape(15.dp)
                            )
                    ) {
                        Column(Modifier.padding(10.dp)) {
                            Text("2023第41屆新一代設計展", fontWeight = FontWeight.SemiBold)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Text("YODEX", fontSize = 20.sp)
                                OutlinedButton(onClick = { }) {
                                    Text("購票")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}