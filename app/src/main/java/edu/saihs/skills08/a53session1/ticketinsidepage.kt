package edu.saihs.skills08.a53session1

import android.R.attr.shape
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.Illuminant.B
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun ticketinsidepage(navController: NavHostController, viewModel: MyViewModel = viewModel()) {
    val amount by viewModel.numbers.observeAsState(initial = emptyList())
    var name = listOf("一日票", "雙日票", "優待票", "敬老票", "學生票")
    var total = 0
    var context = LocalContext.current
    var text1 by remember() { mutableStateOf("") }
    var text2 by remember() { mutableStateOf("") }
    var text3 by remember() { mutableStateOf("") }
    var text4 by remember() { mutableStateOf("") }
    var text5 by remember() { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 5.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 5.dp)
                .border(width = 2.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Column(
                    modifier = Modifier.padding(horizontal = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("票種", color = Color(0xff707070), modifier = Modifier.padding(15.dp))
                    amount.forEachIndexed { index, it ->
                        if (it != 0) {
                            Text(
                                name[index],
                                modifier = Modifier.padding(vertical = 5.dp),
                                color = Color(0xff11617F)
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier.padding(horizontal = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("價格", color = Color(0xff707070), modifier = Modifier.padding(15.dp))
                    amount.forEach {
                        if (it != 0) {
                            Text(
                                "NT 320",
                                modifier = Modifier.padding(vertical = 5.dp),
                                color = Color(0xff11617F)
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier.padding(horizontal = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("數量", color = Color(0xff707070), modifier = Modifier.padding(15.dp))
                    amount.forEach {
                        if (it != 0) {
                            Text(
                                it.toString(),
                                modifier = Modifier.padding(vertical = 5.dp),
                                color = Color(0xff11617F)
                            )
                            total += 320 * it
                        }
                    }
                }
            }
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("票價")
                Text(total.toString())
            }
        }
        Text("購票人資料", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text("姓名")
        OutlinedTextField(
            value = text1,
            onValueChange = { text1 = it },
            modifier = Modifier
                .padding(vertical = 10.dp)
                .size(400.dp, 50.dp)
        )
        Text("Email")
        OutlinedTextField(
            value = text2,
            onValueChange = { text2 = it },
            modifier = Modifier
                .padding(vertical = 10.dp)
                .size(400.dp, 50.dp)
        )
        Text("電話")
        OutlinedTextField(
            value = text3,
            onValueChange = { text3 = it },
            modifier = Modifier
                .padding(vertical = 10.dp)
                .size(400.dp, 50.dp)
        )
        Text("日期")
        OutlinedTextField(
            value = text4,
            onValueChange = { text4 = it },
            modifier = Modifier
                .padding(vertical = 10.dp)
                .size(400.dp, 50.dp)
        )
        Text("付款方式")
        OutlinedTextField(
            value = text5,
            onValueChange = { text5 = it },
            modifier = Modifier
                .padding(vertical = 10.dp)
                .size(400.dp, 50.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                onClick = { navController.navigate("ticket") },
                shape = RoundedCornerShape(10.dp),
            ) {
                Text("上一頁")
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Button(
                onClick = {
                    if (text1.length > 15 || text1 == "") {
                        Toast.makeText(context, "姓名格式錯誤", Toast.LENGTH_SHORT).show()
                    } else if (text2.length > 30 || android.util.Patterns.EMAIL_ADDRESS.matcher(
                            text2
                        ).matches() == false
                    ) {
                        Toast.makeText(context, "Email格式錯誤", Toast.LENGTH_SHORT).show()
                    } else if (text3.matches(Regex("^09\\d{2}-\\d{3}-\\d{3}$")) == false) {
                        Toast.makeText(context, "電話格式錯誤", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "送出成功", Toast.LENGTH_SHORT).show()
                    }
                },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(Color(0xff11617F))
            ) {
                Text("確認購買")
            }
        }

    }

}