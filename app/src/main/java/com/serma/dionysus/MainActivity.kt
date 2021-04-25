package com.serma.dionysus

import com.serma.dionysus.ui.eventinfo.EventInfoScreen
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.serma.dionysus.common.common.ui.PersonData
import com.serma.dionysus.ui.ProfileData
import com.serma.dionysus.ui.ProfileScreen
import com.serma.dionysus.ui.eventinfo.EventInfoData


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val testData = PersonData("Максим Яковлев", "https://s0.rbk.ru/v6_top_pics/media/img/5/46/756038770746465.jpg")
        val listTestData = listOf(testData, testData, testData, testData)

        val data = EventInfoData(
            "Сдерживание грузина",
            "Завтра",
            "Сдерживание мощного, не молодого грузина посредством применения специально оборудованных водометов",
            listTestData,
            "Политех",
            "300$"
        )
        setContent {
            EventInfoScreen(data)
            //EventScreen()
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