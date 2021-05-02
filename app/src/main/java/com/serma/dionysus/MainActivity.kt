package com.serma.dionysus

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.android.material.datepicker.MaterialDatePicker
import com.serma.dionysus.ui.profile.ProfileScreen
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DionysusComposeApp(this::openDatePicker)
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
//        ProfileScreen(
////            ProfileData(
////                "https://lh3.googleusercontent.com/proxy/XtFXriM2QoI-FZaFGc_pwO13_TbmBUl0d4ZTcSmyTMTpnjglEDFSgru8qoI0oJqmEmfKNIYiwCXsTKxp3Ns90T1rL1E",
////                "Тодд Говард",
////                "12-11-1999",
////                "Бесезда",
////            )
//
//        )
    }


    private fun openDatePicker(callback: (Long) -> Unit) {
        val picker = MaterialDatePicker.Builder.datePicker().build()
        picker.show(supportFragmentManager, picker.toString())
        picker.addOnPositiveButtonClickListener {
            callback(it)
        }
    }
}

fun interface OpenDatePicker {
    fun openDatePicker(callback: (Long) -> Unit)
}