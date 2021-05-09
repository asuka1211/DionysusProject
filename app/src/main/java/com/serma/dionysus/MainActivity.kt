package com.serma.dionysus

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.android.material.datepicker.MaterialDatePicker
import com.serma.dionysus.auth.manager.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DionysusComposeApp(this::openDatePicker, sessionManager)
        }
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