package com.example.quizfeed

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.MotionEvent

class QuestionOption(
    private var x: Float,
    private var y: Float,
    private var description: String,
    isRight: Boolean,
): GameObject {
    private val initialX = x.toInt()
    private val initialY = y.toInt()

    val isRightQuestion = isRight
    private val textPaint = Paint()
    private val rectPaint = Paint()
    private val borderPaint = Paint()

    fun getX(): Float {
        return x
    }

    fun getY(): Float {
        return y
    }

    init {
        rectPaint.color = Color.rgb(240, 240, 240)
        borderPaint.color = Color.BLACK

        textPaint.color = Color.BLACK
        textPaint.typeface = Fonts.bangers
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.textSize = 65f
    }

    private var caught = false

    override fun update(et: Float) {}

    override fun render(canvas: Canvas) {
        canvas.drawRoundRect(
            (x - 205),
            (y - 132),
            (x + 205),
            (y + 111),
            40f,
            40f,
            borderPaint,
        )

        canvas.drawRoundRect(
            (x - 200),
            (y - 128),
            (x + 200),
            (y + 108),
            40f,
            40f,
            rectPaint,
        )

        canvas.drawText(description, x, y, textPaint)
    }

    override fun handleEvent(event: Int, x: Float, y: Float) {
        if (event == MotionEvent.ACTION_DOWN) {
            if (Rect(
                    (this.x - 200).toInt(),
                    (this.y - 128).toInt(),
                    (this.x + 200).toInt(),
                    (this.y + 108).toInt(),
                ).contains(x.toInt(), y.toInt())) {
                caught = true
            }
        }
        else if (event == MotionEvent.ACTION_MOVE) {
            if (caught) {
                this.x = x
                this.y = y
            }
        }
        else if (event == MotionEvent.ACTION_UP) {
            caught = false
            this.x = initialX + 1f
            this.y = initialY + 1f
        }
    }
}