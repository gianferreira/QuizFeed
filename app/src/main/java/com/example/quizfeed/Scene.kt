package com.example.quizfeed

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.view.MotionEvent

interface Scene {

    fun update(et: Float)
    fun render(canvas: Canvas)
    fun onTouch(e: MotionEvent): Boolean
    fun onResume()
    fun onPause()

    fun loadBitmap(file: String, context: Context): Bitmap {
        val inputStream = context.assets.open(file)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream.close()
        return bitmap
    }
}