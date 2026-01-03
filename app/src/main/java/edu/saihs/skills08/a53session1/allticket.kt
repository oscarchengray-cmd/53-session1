package edu.saihs.skills08.a53session1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Database
import kotlinx.coroutines.launch


@Composable
fun allticketpage(db: AppDatabase){
    var a: List<Ticket> = remember { mutableStateListOf() }
    val scope = rememberCoroutineScope()
    scope.launch {
        a=db.dao.getAllTickets()
    }

    LazyColumn() {
        itemsIndexed(a){index,it->
            Row (modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Column() {
                    Text("2022第41屆新一代設計展")
                    Spacer(modifier = Modifier.padding(vertical = 20.dp))
                }
            }
        }
    }
}