package edu.saihs.skills08.a53session1

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import okio.blackholeSink


@Composable
fun floorpage(){
    var click by remember { mutableStateOf(0) }

    Column() {
        Row() {
            for (i in 1..2)
                OutlinedButton(onClick = {if (i==1) click=1 else click=2}, shape = RoundedCornerShape(0.dp), modifier = Modifier.padding(10.dp),) {
                    Text(if (i==1) "一館" else "二館", color = Color.Black, fontWeight = FontWeight.Bold)
                }
        }
        if (click==1){
            Image(
                painter = painterResource(id=R.drawable._6),null,
                modifier = Modifier.fillMaxSize()
            )
        }else if (click==2){
            Image(
                painter = painterResource(id=R.drawable._7),null,
                modifier = Modifier.fillMaxSize()
            )
        }else{

        }
    }


}

