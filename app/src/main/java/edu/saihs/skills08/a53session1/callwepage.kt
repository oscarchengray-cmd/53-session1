package edu.saihs.skills08.a53session1

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun callwepage(){
    var context = LocalContext.current
    var text1 by remember() { mutableStateOf("") }
    var text2 by remember() { mutableStateOf("") }
    var text3 by remember() { mutableStateOf("") }
    var text4 by remember() { mutableStateOf("") }
    Column() {
        OutlinedTextField(
            value = text1,
            onValueChange = {text1 = it},
            label = { Text("標題") },
            modifier = Modifier.padding(20.dp).size(500.dp,80.dp)
        )
        OutlinedTextField(
            value = text2,
            onValueChange = {text2 = it},
            label = { Text("姓名") },
            modifier = Modifier.padding(20.dp).size(500.dp,80.dp)
        )
        OutlinedTextField(
            value = text3,
            onValueChange = {text3 = it},
            label = { Text("電話") },
            modifier = Modifier.padding(20.dp).size(500.dp,80.dp)
        )
        OutlinedTextField(
            value = text4,
            onValueChange = {text4 = it},
            label = { Text("Email") },
            modifier = Modifier.padding(20.dp).size(500.dp,80.dp)
        )
        Row(modifier = Modifier.fillMaxWidth().padding(10.dp), horizontalArrangement = Arrangement.Center) {
            Button(
                onClick = {
                    if (text1.length>30){
                        Toast.makeText(context, "標題過長", Toast.LENGTH_SHORT).show()
                    }else if (text2.length>15){
                        Toast.makeText(context, "姓名過長", Toast.LENGTH_SHORT).show()
                    }else if (text3.matches(Regex("^09\\d{2}-\\d{3}-\\d{3}$"))==false){
                        Toast.makeText(context, "電話格式錯誤", Toast.LENGTH_SHORT).show()
                    }else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(text4).matches()!=false){
                        Toast.makeText(context, "email格式錯誤", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context, "送出成功", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text("送出")
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Button(
                onClick = {
                    text1=""
                    text2=""
                    text3=""
                    text4=""
                }
            ) {
                Text("重填")
            }
        }

    }

}