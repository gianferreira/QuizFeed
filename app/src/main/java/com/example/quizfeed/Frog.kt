package com.example.quizfeed

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect

class Frog(
    context: Context,
    private var x: Float,
    private var y: Float,
): GameObject {

    private var frogEatingArray: MutableList<Bitmap?> = mutableListOf()
    private var frogWaitingArray: MutableList<Bitmap?> = mutableListOf()
    private var frog: Bitmap
    private var src = Rect()
    private var dst = Rect()

    private var currentFrogState = 0
    private var animationTime = 0f
    private var eatingTime = 0f
    private var changeTime = 250f
    private var isEating = false

    init {
        frogEatingArray.addAll(
            arrayOf(
                loadBitmap("img-eating-01.png", context),
                loadBitmap("img-eating-02.png", context),
                loadBitmap("img-eating-03.png", context),
            )
        )

        frogWaitingArray.addAll(
            arrayOf(
                loadBitmap("img-waiting-01.png", context),
                loadBitmap("img-waiting-06.png", context),
                loadBitmap("img-waiting-02.png", context),
                loadBitmap("img-waiting-04.png", context),
                loadBitmap("img-waiting-02.png", context),
                loadBitmap("img-waiting-04.png", context),
                loadBitmap("img-waiting-05.png", context),
                loadBitmap("img-waiting-05.png", context),
                loadBitmap("img-waiting-05.png", context),
                loadBitmap("img-waiting-06.png", context),
            )
        )

        frog = frogWaitingArray.first()!!
    }

    override fun update(et: Float) {
        animationTime += et

        if (animationTime >= changeTime) {
            animationTime = 0f

            if(isEating) {
                eatingTime += et

                if (eatingTime >= 200f) {
                    eatingTime = 0f
                    isEating = false
                }

                changeTime = 150f
                currentFrogState = if(currentFrogState >= 2) 0 else currentFrogState + 1
                frog = frogEatingArray[currentFrogState] ?: frog
            } else {
                changeTime = 150f
                currentFrogState = if(currentFrogState >= 9) 0 else currentFrogState + 1
                frog = frogWaitingArray[currentFrogState] ?: frog
            }
        }
    }

    override fun render(canvas: Canvas) {
        src.set(0, 0, frog.width, frog.height)
        dst.set(x.toInt(), y.toInt(), x.toInt() + 384, y.toInt() + 384)

        canvas.drawBitmap(frog, src, dst, null)
    }

    override fun handleEvent(event: Int, x: Float, y: Float) {}

    fun verifyAnswer(option: QuestionOption): Boolean {

        val optionX = option.getX().toInt()
        val optionY = option.getY().toInt()

        if (dst.contains(optionX, optionY)) {
            isEating = true

            return true
        }

        return false
    }


}