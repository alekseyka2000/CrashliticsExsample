package com.example.crashliticsexsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crashliticsexsample.ui.theme.CrashliticsExsampleTheme
import com.google.firebase.crashlytics.FirebaseCrashlytics

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CrashliticsExsampleTheme {
                Surface(color = MaterialTheme.colors.background) {
                    InitCrashButtons()
                }
            }
        }
        FirebaseCrashlytics.getInstance().setUserId("user name")
    }
}

@Composable
fun InitCrashButtons() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        CrashButton("Crash 1") { throw RuntimeException("Crash") }
        CrashButton("Crash 2") { val value = 2 / 0 }
        CrashButton("Crash 3") {
            val value: Int? = null
            value!! / 2
        }
    }
}

@Composable
fun CrashButton(buttonName: String, buttonAction: () -> Unit) {
    Button(
        onClick = {
            buttonAction()
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(5.dp)
    ) {
        Text(text = buttonName, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CrashliticsExsampleTheme {
        InitCrashButtons()
    }
}