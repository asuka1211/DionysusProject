package com.serma.dionysus.common.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.serma.dionysus.utils.ColorHelper

private val sectionPaint = Paint().apply {
    isAntiAlias = true
    style = PaintingStyle.Stroke
}

@Composable
fun <T : SliceData> CommonCircularGraph(data: GraphData<T>, modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier.fillMaxSize()
    ) {

        drawIntoCanvas {
            var startAngel = 0f
            val drawableArea = calculateArea(area = size)
            val sliceThickness = getThickness(area = size)

            data.slices.forEach { slice ->
                it.drawArc(
                    drawableArea,
                    startAngel,
                    sweepAngle = calculateAngel(slice.progress, data.totalProgress),
                    useCenter = false,
                    paint = sectionPaint.apply {
                        color = slice.color
                        strokeWidth = sliceThickness
                    }
                )
                startAngel += calculateAngel(slice.progress, data.totalProgress)
            }
        }
    }
}

@Composable
fun <T: SliceData> InfoForGraph(
    data: GraphData<T>,
    modifier: Modifier = Modifier,
    donutSize: Dp,
    itemContent: @Composable (T) -> Unit
) {
    Column {
        CommonCircularGraph(
            GraphData(data.slices),
            Modifier
                .fillMaxWidth()
                .height(donutSize)
                .align(Alignment.CenterHorizontally)
        )
        SpacerRow(height = 16)
        LazyColumn(modifier = modifier) {
            items(data.slices) { slice ->
                Row(modifier.padding(top = 16.dp)) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = slice.color,
                                RoundedCornerShape(12.dp)
                            )
                            .width(60.dp)
                            .height(24.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(Modifier.width(16.dp))
                    itemContent(slice)
                }
            }
        }
    }
}

private fun calculateAngel(progress: Float, totalProgress: Float): Float {
    return 360f / (totalProgress / progress)
}

private fun getThickness(area: Size, sliceThickness: Float = 25f): Float {
    val minSize = minOf(area.width, area.height)

    return minSize * (sliceThickness / 200f)
}

private fun calculateArea(area: Size): Rect {
    val sliceThicknessOffset =
        getThickness(area = area) / 2f
    val offsetHorizontally = (area.width - area.height) / 2f

    return Rect(
        left = sliceThicknessOffset + offsetHorizontally,
        top = sliceThicknessOffset,
        right = area.width - sliceThicknessOffset - offsetHorizontally,
        bottom = area.height - sliceThicknessOffset
    )
}

data class GraphData<T : SliceData>(val slices: List<T>) {
    val totalProgress
        get() = slices.fold(0f) { acc, e -> acc + e.progress }
}

interface SliceData {
    val progress: Float
    val color: Color
}
