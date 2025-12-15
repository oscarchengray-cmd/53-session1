package edu.saihs.skills08.a53session1

import android.icu.text.CaseMap
import android.os.Bundle
import android.util.Log.i
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import edu.saihs.skills08.a53session1.ui.theme._53Session1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _53Session1Theme {
                home()
            }
        }
    }
}

data class Media(
    val title: String,
    val dateTime: String,
    val hall: List<String>,
    val content: String
)

@Composable
fun home() {
    var context = LocalContext.current
    val json = context.assets
        .open("媒體中心.json")
        .bufferedReader()
        .use {
            it.readText()
        }

    var diamdiam by remember { mutableStateOf(0) }
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(Color.White)
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Default.Menu, null,
                        tint = Color(0xff2B6F82)
                    )
                }
                Image(
                    painter = painterResource(R.drawable.nanggan),
                    contentDescription = null,
                    modifier = Modifier
                        .height(40.dp)
                        .align(Alignment.Center),
                    contentScale = ContentScale.FillHeight
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White),
            Arrangement.Top
        ) {
            val pagerState = rememberPagerState(pageCount = { 2 })
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth()
            ) { page ->
                diamdiam = page
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
                    Box(
                        modifier = Modifier
                            .animateContentSize()
                            .size(
                                if (diamdiam == 0 && i == 1) 20.dp else if (diamdiam == 1 && i == 2) 20.dp else 10.dp,
                                10.dp
                            )
                            .background(
                                color = if (diamdiam == 0 && i == 1) Color.DarkGray else if (diamdiam == 1 && i == 2) Color.DarkGray else Color.Gray,
                                shape = RoundedCornerShape(10.dp)
                            )
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                }
            }
            Column() {
                val a: List<Media> = Gson().fromJson(json, Array<Media>::class.java).toList()
                LazyColumn() {
                    item {Text("媒體中心", modifier = Modifier.padding(10.dp)) }
                    items(a) {item ->
                        Row(modifier = Modifier.padding(vertical = 10.dp)) {
                            Column(modifier = Modifier.padding(horizontal = 10.dp).width(100.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                Text(text = item.dateTime, color = Color.Gray)
                                Spacer(modifier = Modifier.padding(3.dp))
                                Row {
                                    item.hall.forEach { it ->
                                        Text(text = it, color = if(it=="1館") Color(0xff11617f) else Color(0xff1AAB9F))
                                        Spacer(modifier = Modifier.padding(3.dp))
                                    }
                                }

                            }
                            Text(item.title)
                        }
                        HorizontalDivider(thickness = 1.dp)
                    }
                }
            }
        }
    }
}