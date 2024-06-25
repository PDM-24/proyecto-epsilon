package com.hnrylvo.inmomarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.hnrylvo.inmomarket.ui.theme.InmoMarketTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InmoMarketTheme {

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AppPreview() {

}