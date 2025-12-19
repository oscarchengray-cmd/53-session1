package edu.saihs.skills08.a53session1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import edu.saihs.skills08.a53session1.ui.theme._53Session1Theme
import edu.saihs.skills08.a53session1.ui.theme.artpage
import kotlinx.coroutines.launch

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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun home() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val drawer = listOf("關於展館", "樓層立體圖", "公共藝術", "聯絡我們", "全部票卡", "主畫面")
    val about = listOf("經營者", "展館介紹")
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                drawer.forEachIndexed {index,it->
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(Modifier
                            .padding(start = 10.dp)
                            .size(20.dp, 20.dp)
                            .background(color = Color(0xff11617F))) {
                        }
                        NavigationDrawerItem(
                            label = { Text(it) },
                            selected = false,
                            onClick = {
                                when(index){
                                    1-> {
                                        navController.navigate("3dfloor")
                                        scope.launch {
                                            drawerState.close()
                                        }
                                    }
                                    5-> {
                                        navController.navigate("main")
                                        scope.launch {
                                            drawerState.close()
                                        }
                                    }
                                }

                            },
                            modifier = Modifier.size(height = 50.dp,width = 200.dp)
                        )
                    }

                    if (it == "關於展館") {
                        about.forEachIndexed {index,it->
                            Row(
                                modifier = Modifier
                                    .padding(start = 40.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.e),
                                    null,
                                    modifier = Modifier.size(20.dp)
                                )
                                NavigationDrawerItem(
                                    label = { Text(it) },
                                    selected = false,
                                    onClick = {
                                        if (index==0) navController.navigate("owner") else navController.navigate("introduce")
                                        scope.launch {
                                            drawerState.close()
                                        }
                                    },
                                    modifier = Modifier.size(height =  40.dp,width = 200.dp)
                                )
                            }
                        }
                    }
                }
            }
        },
        gesturesEnabled = false
        ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Scaffold(
            topBar = {
                if (currentRoute.toString() == "main inside/{context}") {
                    TopAppBar(
                        title = { "" },
                        navigationIcon = {
                            IconButton(onClick = { navController.navigate("main") }) {
                                Icon(
                                    Icons.Default.KeyboardArrowLeft, null,
                                    tint = Color(0xff2B6F82),
                                    modifier = Modifier.size(50.dp)

                                )

                            }

                        }
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .statusBarsPadding()
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) drawerState.open() else drawerState.close()
                            }
                        }) {
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
            }
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                NavHost(
                    navController = navController, startDestination = "main"
                ) {
                    composable("main") { mainpage(navController) }
                    composable(
                        route = "main inside/{context}",
                        arguments = listOf(navArgument("context") { type = NavType.IntType })
                    ) { it ->
                        val page2id = it.arguments?.getInt("context") ?: 0
                        maininsidepage(navController, page2id)
                    }
                    composable("3dfloor") { floorpage() }
                    composable("art") { artpage() }
                    composable("introduce") { introducepage() }
                    composable("owner") { ownerpage() }

                }
            }
        }
    }
}

