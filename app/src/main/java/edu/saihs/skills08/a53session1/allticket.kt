package edu.saihs.skills08.a53session1

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Database
import kotlinx.coroutines.launch


@Composable
fun allticketpage(db: AppDatabase){
    val a by db.dao.getAllTickets().collectAsState(emptyList())
    var name = listOf("一日票", "雙日票", "優待票", "敬老票", "學生票")
    var money=0
    LazyColumn() {
        itemsIndexed(a){index,it->
            Row (modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp).border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp)).height(150.dp), horizontalArrangement = Arrangement.SpaceBetween,) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text("2022第41屆新一代設計展", fontSize = 20.sp,color = Color(0xff11617F))
                    Spacer(modifier = Modifier.padding(vertical = 10.dp))
                    Text(it.name, fontSize = 25.sp)
                    Text(it.id.toString(), fontSize = 20.sp)
                    Text(it.date, fontSize = 15.sp)
                }
                Column(modifier = Modifier.fillMaxHeight().padding(10.dp), horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.Bottom) {
                    it.ticketList.forEachIndexed {index,it->
                        if (it != 0) {
                            money+=it*320
                            Text(
                                name[index],
                                modifier = Modifier.padding(vertical = 5.dp),
                            )
                        }
                    }
                    Text("NT $money")
                }
            }
        }
    }
}