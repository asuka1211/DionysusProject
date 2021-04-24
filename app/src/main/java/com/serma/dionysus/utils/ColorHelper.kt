package com.serma.dionysus.utils

import androidx.compose.ui.graphics.Color
import javax.inject.Inject

class ColorHelper @Inject constructor() {

    companion object {
        private val DEFAULT_COLOR = Color.Cyan
    }

    private val colors: List<Color> = listOf(
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

    private val usedColors: MutableList<Color> = mutableListOf()

    fun getColor(): Color {
        val freeColors = colors - usedColors
        if (freeColors.isEmpty()) {
            usedColors.clear()
            return DEFAULT_COLOR
        }
        val color = freeColors[(Math.random() * freeColors.size).toInt()]
        usedColors.add(color)
        return color
    }
}