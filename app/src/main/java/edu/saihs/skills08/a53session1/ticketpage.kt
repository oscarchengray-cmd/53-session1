package edu.saihs.skills08.a53session1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ticketpage(){
    var amount: List<Int> = emptyList()
    var name =listOf("一日票", "雙日票","優待票","敬老票","學生票")
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text("票種", color = Color(0xff707070), modifier = Modifier.padding(15.dp))
            name.forEach {
                Text(it, modifier = Modifier.padding(10.dp))
            }
        }
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text("價格", color = Color(0xff707070), modifier = Modifier.padding(15.dp))
            name.forEach {
                Text("NT 320", modifier = Modifier.padding(10.dp))
            }
        }
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text("價格", color = Color(0xff707070), modifier = Modifier.padding(15.dp))
            name.forEach {
                Text("NT 320", modifier = Modifier.padding(10.dp))
            }
        }

    }
}