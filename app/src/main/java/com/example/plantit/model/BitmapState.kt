package com.example.plantit.model

import android.graphics.Bitmap

data class BitmapState(
    val selectedImageBitmap: Bitmap? = null,
    val isLoading : Boolean = false,
    val response : String? = null
)