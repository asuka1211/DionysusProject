package com.google.accompanist.sample.pager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.serma.dionysus.ui.tasklist.TaskData
import com.serma.dionysus.ui.tasklist.TaskList

@Preview
@Composable
fun PagerPreview() {
    Sample()
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun Sample() {
    val testData = TaskData(
        "Тупое говно",
        "Тупого говна",
        "Вчера",
        ""
    )
    val testData2 = TaskData(
        "Тупое говно",
        "Тупого говна",
        "Вчера",
        "Родительская задача"
    )
    val listTestData = listOf(testData, testData2, testData)
    // Display 10 items
    val pagerState = rememberPagerState(pageCount = 10)

    HorizontalPager(state = pagerState) { page ->
        TaskList(listTestData)
    }
}