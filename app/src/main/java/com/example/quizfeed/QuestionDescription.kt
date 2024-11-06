package com.example.quizfeed

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect

class QuestionDescription(
    private val screen: MainActivity.Screen,
    private var question: QuestionObject,
): GameObject {

    private val textPaint = Paint()
    private val rectPaint = Paint()
    private val borderPaint = Paint()

    private var questionFrog: Bitmap
    private var src = Rect()
    private var dst = Rect()

    init {
        rectPaint.color = Color.rgb(240, 240, 240)
        borderPaint.color = Color.BLACK

        textPaint.color = Color.BLACK
        textPaint.typeface = Fonts.bangers
        textPaint.textAlign = Paint.Align.LEFT
        textPaint.textSize = 60f

        questionFrog = loadBitmap(question.questionImage, screen.context)!!
    }

    override fun update(et: Float) {}

    override fun render(canvas: Canvas) {
        canvas.drawRoundRect(
            59f,
            screen.height - 891f,
            screen.width - 59f,
            screen.height - 1285f,
            40f,
            40f,
            borderPaint,
        )

        canvas.drawRoundRect(
            64f,
            screen.height - 896f,
            screen.width - 64f,
            screen.height - 1280f,
            40f,
            40f,
             rectPaint,
        )

        canvas.drawText(question.description, 128f, screen.height /2f + 48f, textPaint)
        canvas.drawText(question.complement, 128f, screen.height /2f + 144f, textPaint)

        src.set(0, 0, questionFrog.width, questionFrog.height)
        dst.set(
            screen.width - 320,
            screen.height - 1068,
            screen.width - 64,
            screen.height - 812,
        )

        canvas.drawBitmap(questionFrog, src, dst, null)
    }

    override fun handleEvent(event: Int, x: Float, y: Float) {}
}