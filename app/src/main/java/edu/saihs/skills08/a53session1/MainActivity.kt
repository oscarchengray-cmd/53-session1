package edu.saihs.skills08.a53session1

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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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

@Composable
fun home() {
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
            Row(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(), Arrangement.End) {
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
        }
    }
}




