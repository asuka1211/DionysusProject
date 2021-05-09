package com.google.accompanist.sample.pager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.serma.dionysus.common.theme.BackgroundColor
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
        "Привер тэга",
        "Пример имени",
        "Сегодня",
        "",
        0
    )
    val testData2 = TaskData(
        "Привер тэга",
        "Пример имени2",
        "Сегодня",
        "",
        1
    )
    val testData3 = TaskData(
        "Привер тэга",
        "Пример имени3",
        "Сегодня",
        "Родительская задача",
        1
    )
    val testData4 = TaskData(
        "Привер тэга",
        "Пример имени4",
        "Сегодня",
        "Родительская задача",
        2
    )
    val listTestData = listOf(testData, testData2, testData3, testData4)

    Surface(modifier = Modifier.background(BackgroundColor)) {
        Column(Modifier.fillMaxSize()) {
            val pagerState = rememberPagerState(
                pageCount = 3
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
            ) { page ->
                TaskList(listTestData, page)
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
            )
        }
    }
}