package com.serma.dionysus.common.ui

import androidx.compose.animation.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun CommonGraphPreview() {
    val list = listOf(
        SliceData(10f),
        SliceData(2f),
        SliceData(5f)
    )
    CommonCircularGraph(GraphData(list), Modifier.height(100.dp).width(100.dp))
}

private val sectionPaint = Paint().apply {
    isAntiAlias = true
    style = PaintingStyle.Stroke
}

@Composable
fun CommonCircularGraph(data: GraphData, modifier: Modifier = Modifier) {
    Canvas(
        modifier = modifier
    ) {

        drawIntoCanvas {
            var startAngel = 0f
            val drawableArea = calculateDrawableArea(area = size)
            val sliceThickness = calculateSectorThickness(area = size)

            data.slices.forEach { slice ->
                it.drawArc(
                    drawableArea,
                    startAngel,
                    sweepAngle = calculateAngel(slice.progress, data.totalProgress),
                    useCenter = false,
                    paint = sectionPaint.apply {
                        color = colors[(Math.random() * colors.size).toInt()]
                        strokeWidth = sliceThickness
                    }
                )
                startAngel += calculateAngel(slice.progress, data.totalProgress)
            }
        }
    }
}

private fun calculateAngel(progress: Float, totalProgress: Float): Float {
    return 360f / (totalProgress / progress)
}

private fun calculateSectorThickness(area: Size, sliceThickness: Float = 25f): Float {
    val minSize = minOf(area.width, area.height)

    return minSize * (sliceThickness / 200f)
}

private fun calculateDrawableArea(area: Size): Rect {
    val sliceThicknessOffset =
        calculateSectorThickness(area = area) / 2f
    val offsetHorizontally = (area.width - area.height) / 2f

    return Rect(
        left = sliceThicknessOffset + offsetHorizontally,
        top = sliceThicknessOffset,
        right = area.width - sliceThicknessOffset - offsetHorizontally,
        bottom = area.height - sliceThicknessOffset
    )
}

private var colors = mutableListOf(
    Color(0XFFF44336),
    Color(0XFFE91E63),
    Color(0XFF9C27B0),
    Color(0XFF673AB7),
    Color(0XFF3F51B5),
    Color(0XFF03A9F4),
    Color(0XFF009688),
    Color(0XFFCDDC39),
    Color(0XFFFFC107),
    Color(0XFFFF5722),
    Color(0XFF795548),
    Color(0XFF9E9E9E),
    Color(0XFF607D8B)
)

data class GraphData(val slices: List<SliceData>) {
    val totalProgress
        get() = slices.fold(0f) { acc, e -> acc + e.progress }
}

data class SliceData(val progress: Float)
