package com.example.plantit.core.presentation.theme

import androidx.compose.foundation.shape.GenericShape
import androidx.compose.ui.graphics.Path

val BottomArcShape = GenericShape { size, _ ->
    val path = Path()

    path.moveTo(0f, 0f)
    path.lineTo(0f, size.height * 0.85f)

    path.quadraticTo(
        size.width / 2f,
        size.height + 60f,
        size.width,
        size.height * 0.85f
    )

    path.lineTo(size.width, 0f)
    path.close()

    addPath(path)
}
