package com.example.quizfeed

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.MotionEvent

class StartScene(
    private val screen: MainActivity.Screen,
    private var points: Int,
    private var endGame: Boolean,
    private var currentQuestion: Int,
): Scene {

    private val textPaint = Paint()
    private val rectPaint = Paint()
    private val borderPaint = Paint()
    private val titlePaint = Paint()
    private val titleBorderPaint = Paint()

    private var backgroundBitmap: Bitmap? = null
    private var actionText = ""
    private var pointsText =  ""
    private var resetText = ""
    private var quizText = ""
    private var feedText = ""

    private var soundPoolWrapper: SoundPoolWrapper

    init {
        val descriptors = arrayOf(screen.context.assets.openFd("song-start.ogg"))

        soundPoolWrapper = SoundPoolWrapper(descriptors)

        rectPaint.color = Color.rgb(240, 240, 240)
        borderPaint.color = Color.BLACK

        textPaint.color = Color.BLACK
        textPaint.typeface = Fonts.bangers
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.textSize = 65f

        titlePaint.color = Color.rgb(154, 205, 0)
        titlePaint.typeface = Fonts.bangers
        titlePaint.textAlign = Paint.Align.CENTER
        titlePaint.textSize = 350f

        titleBorderPaint.color = Color.BLACK
        titleBorderPaint.typeface = Fonts.bangers
        titleBorderPaint.textAlign = Paint.Align.CENTER
        titleBorderPaint.textSize = 385f

        backgroundBitmap = loadBitmap("img-background.jpg", screen.context)
    }

    override fun update(et: Float) {
        quizText = "QUIZ"
        feedText = "FEED"

        actionText = if(currentQuestion == 0) "Toque para iniciar." else "Toque para continuar."
        pointsText = "Pontuação atual: $points"
        resetText = "Resetar partida atual."

        if(endGame) {
            actionText = "Começar novamente."
            pointsText = "Pontuação final: $points"
        }
    }

    override fun render(canvas: Canvas) {
        val background = backgroundBitmap ?: return

        val src = Rect(128, 128, 512, 768)
        val dst = Rect(0, 0, screen.width, screen.height)

        canvas.drawBitmap(background, src, dst, null)

        canvas.drawRoundRect(
            (95f),
            (screen.height - 1205f),
            (screen.width - 95f),
            (screen.height - 945f),
            40f,
            40f,
            borderPaint,
        )
        canvas.drawRoundRect(
            (100f),
            (screen.height - 1200f),
            (screen.width - 100f),
            (screen.height - 950f),
            40f,
            40f,
            rectPaint,
        )

        canvas.drawRoundRect(
            (95f),
            (screen.height - 855f),
            (screen.width - 95f),
            (screen.height - 595f),
            40f,
            40f,
            borderPaint,
        )
        canvas.drawRoundRect(
            (100f),
            (screen.height - 850f),
            (screen.width - 100f),
            (screen.height - 600f),
            40f,
            40f,
            rectPaint,
        )

        canvas.drawRoundRect(
            (95f),
            (screen.height - 505f),
            (screen.width - 95f),
            (screen.height - 245f),
            40f,
            40f,
            borderPaint,
        )
        canvas.drawRoundRect(
            (100f),
            (screen.height - 500f),
            (screen.width - 100f),
            (screen.height - 250f),
            40f,
            40f,
            rectPaint,
        )

        canvas.drawText(quizText, screen.width/2f, 650f, titleBorderPaint)
        canvas.drawText(feedText, screen.width/2f, 850f, titleBorderPaint)
        canvas.drawText(quizText, screen.width/2f, 650f, titlePaint)
        canvas.drawText(feedText, screen.width/2f, 850f, titlePaint)

        canvas.drawText(actionText, screen.width/2f, screen.height - 1050f, textPaint)
        canvas.drawText(pointsText, screen.width/2f, screen.height - 700f, textPaint)
        canvas.drawText(resetText, screen.width/2f, screen.height - 350f, textPaint)
    }

    override fun onTouch(e: MotionEvent): Boolean {
        return when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                if (Rect(
                        100,
                        screen.height - 1200,
                        screen.width - 100,
                        screen.height - 950,
                    ).contains(e.x.toInt(), e.y.toInt())) {

                    //soundPool.autoPause()
                    //soundPool.release()
                    soundPoolWrapper.dispose()

                    screen.scene = GameScene(
                        screen,
                        if(endGame) 0 else currentQuestion,
                        if(endGame) 0 else points,
                    )
                }

                if (Rect(
                        100,
                        screen.height - 500,
                        screen.width - 100,
                        screen.height - 250,
                    ).contains(e.x.toInt(), e.y.toInt())) {

                    points = 0
                    currentQuestion = 0
                }

                true
            }
            else -> false
        }
    }

    override fun onResume() {}

    override fun onPause() {
        soundPoolWrapper.dispose()
    }
}