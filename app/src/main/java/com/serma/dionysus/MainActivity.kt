package com.serma.dionysus

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.changedToDown
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.serma.dionysus.common.ui.CommonGraphPreview
import com.serma.dionysus.ui.EventScreen
import com.serma.dionysus.ui.ProfileData
import com.serma.dionysus.ui.ProfileScreen
import com.serma.dionysus.ui.login.LoginScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
            //CommonGraphPreview()
        }
    }

    @Preview
    @Composable
    fun Greeting(name: String = "Maxim") {
//        val list = listOf(
//            EventData(
//                "Asd",
//                "Пивная",
//                "12-11-2021",
//                "Насвай пати",
//                0.5f
//            ), EventData(
//                "Asd",
//                "Снюсовая",
//                "21-01-2022",
//                "День выборов",
//                1f
//            ), EventData(
//                "Asd",
//                "Васильсурская 1",
//                "16-11-2022",
//                "День рождение",
//                0f
//            ),
//            EventData(
//                "Asd",
//                "Васильсурasdasdsadsadsa sad asdsa dsad asd asd sad sad asd aская 1",
//                "16-11-2022",
//                "Денsadasdsad asda sdasd asd asda sdasdasdasdsadaь рdsadasdоaasждение",
//                0.3f
//            )
//        )
        // EventsScreen(list, {}, {}, {}, {})
        ProfileScreen(
            ProfileData(
                "https://lh3.googleusercontent.com/proxy/XtFXriM2QoI-FZaFGc_pwO13_TbmBUl0d4ZTcSmyTMTpnjglEDFSgru8qoI0oJqmEmfKNIYiwCXsTKxp3Ns90T1rL1E",
                "Тодд Говард",
                "12-11-1999",
                "Бесезда",
                "Маями",
                "Люблю продавать скайрим "
            )
        )
    }

}