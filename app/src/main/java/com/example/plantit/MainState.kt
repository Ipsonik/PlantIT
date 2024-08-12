package com.example.plantit

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas

data class MainState(
    val selectedImageBitmap: Bitmap? = null,
    val isLoading : Boolean = false,
    val response : String? = null
)





