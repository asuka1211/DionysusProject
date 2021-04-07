package com.serma.dionysus

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.serma.auth.ui.login.LoginScreenPreview

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreenPreview()
        }
    }

    @Preview
    @Composable
    fun Greeting(name: String = "Maxim"){
        Column {
            Text(text = "Hi $name")
        }
    }

}