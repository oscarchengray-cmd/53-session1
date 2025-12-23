package edu.saihs.skills08.a53session1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ticketpage() {
    var amount = remember { mutableStateListOf<Int>(0, 0, 0, 0, 0) }
    var name = listOf("一日票", "雙日票", "優待票", "敬老票", "學生票")
    Column() {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text("票種", color = Color(0xff707070), modifier = Modifier.padding(15.dp))
                name.forEach {
                    Text(it, modifier = Modifier.padding(12.dp))
                }
            }
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text("價格", color = Color(0xff707070), modifier = Modifier.padding(15.dp))
                name.forEach {
                    Text("NT 320", modifier = Modifier.padding(12.dp))
                }
            }
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                Text("價格", color = Color(0xff707070), modifier = Modifier.padding(15.dp))
                amount.forEachIndexed { index, it ->
                    Column() {
                        Row(
                            horizontalArrangement = Arrangement.End
                        ) {
                            IconButton(onClick = { if (amount[index] > 0) amount[index] -= 1 }) {
                                Icon(
                                    painterResource(id = R.drawable.outline_remove_circle_outline_24),
                                    null
                                )
                            }
                            Text(it.toString(), modifier = Modifier.padding(10.dp))
                            IconButton(onClick = { amount[index] += 1 }) {
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
            Button(onClick = {}, shape = RoundedCornerShape(10.dp)) {
                Text("下一步")
            }
        }
    }

}