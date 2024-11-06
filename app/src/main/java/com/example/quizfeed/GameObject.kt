package com.example.quizfeed

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas

interface GameObject {

    fun update(et: Float)
    fun render(canvas: Canvas)
    fun handleEvent(event: Int, x: Float, y: Float)

    fun loadBitmap(file: String, context: Context): Bitmap? {
        val inputStream = context.assets.open(file)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream.close()
        return bitmap
    }
}