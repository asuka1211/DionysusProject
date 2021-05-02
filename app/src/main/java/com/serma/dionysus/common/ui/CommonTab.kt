package com.serma.dionysus.common.ui

import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset

@Deprecated("Used instead default")
@ExperimentalPagerApi
@Composable
fun CommonTabRow(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    selectedPos: Int = 0,
    pagerState: PagerState,
    tabs: @Composable () -> Unit
) {
    TabRow(
        selectedPos,
        backgroundColor = backgroundColor,
        modifier = modifier,
        indicator = { tabPos ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPos)
            )
        }
    ) {
        tabs()
    }
}

@Deprecated("Used instead default")
@ExperimentalPagerApi
@Composable
fun <S, T : BaseTabItem<S>> CommonAutoTabRow(
    modifier: Modifier = Modifier,
    selectedPos: Int = 0,
    backgroundColor: Color,
    pagerState: PagerState,
    data: List<T>,
    tabView: @Composable (T, Int) -> Unit,
) {
    CommonTabRow(
        modifier = modifier,
        selectedPos = selectedPos,
        pagerState = pagerState,
        backgroundColor = backgroundColor
    ) {
        data.forEachIndexed { index, item ->
            tabView(item, index)
        }
    }
}

interface BaseTabItem<T> {
    val pos: Int
    val type: T
}

