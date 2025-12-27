package edu.saihs.skills08.a53session1

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController


@Composable
fun ticketpage(navController: NavHostController, viewModel: MyViewModel = viewModel()) {
    val amount by viewModel.numbers.observeAsState(initial = emptyList())
    var name = listOf("一日票", "雙日票", "優待票", "敬老票", "學生票")
    var total = 0
    var context = LocalContext.current

    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp)),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("票種", color = Color(0xff707070), modifier = Modifier.padding(15.dp))
                name.forEach {
                    Text(it, modifier = Modifier.padding(12.dp), color = Color(0xff11617F))
                }
            }
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("價格", color = Color(0xff707070), modifier = Modifier.padding(15.dp))
                name.forEach {
                    Text("NT 320", modifier = Modifier.padding(12.dp), color = Color(0xff11617F))
                }
            }
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("數量", color = Color(0xff707070), modifier = Modifier.padding(15.dp))
                amount.forEachIndexed { index, it ->
                    Column() {
                        Row(
                            horizontalArrangement = Arrangement.End
                        ) {
                            IconButton(onClick = {
                                if (amount[index] > 0) {
                                    viewModel.updateNumberAt(index, it - 1)
                                }
                            }) {
                                Icon(
                                    painterResource(id = R.drawable.outline_remove_circle_outline_24),
                                    null
                                )
                            }
                            Text(
                                it.toString(),
                                modifier = Modifier.padding(10.dp),
                                color = Color(0xff11617F)
                            )
                            IconButton(onClick = {
                                viewModel.updateNumberAt(index, it + 1)
                            }) {
                                Icon(
                                    painterResource(id = R.drawable.outline_add_circle_24), null
                                )
                            }
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    amount.forEach {
                        total += it
                    }
                    if (total == 0) {
                        Toast.makeText(context, "請選擇", Toast.LENGTH_SHORT).show()
                    } else {
                        navController.navigate("ticketinside")
                    }
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(Color(0xff11617F))
            ) {
                Text("下一步")
            }
        }
    }
}



