package com.example.quizfeed

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.view.MotionEvent

class GameScene(
    private val screen: MainActivity.Screen,
    private var currentQuestion: Int,
    private var points: Int,
): Scene {
    private val title: GameObject = QuestionDescription(
        screen,
        QuizQuestions.getCurrentQuestion(currentQuestion),
    )

    private val frog: Frog = Frog(screen.context, screen.width/2f - 192, 440f)

    private var goArray: MutableList<GameObject> = mutableListOf()
    private var optionsArray: MutableList<QuestionOption> = mutableListOf()

    private var backgroundBitmap: Bitmap? = null
    private var answerPoints = 1000
    private var errorCount = 0

    private var soundPoolWrapper: SoundPoolWrapper

    init {
        val descriptors = arrayOf(
            screen.context.assets.openFd("song-game.ogg"),
            screen.context.assets.openFd("song-wrong.ogg"),
        )

        soundPoolWrapper = SoundPoolWrapper(descriptors)

        backgroundBitmap = loadBitmap("img-background.jpg", screen.context)
        optionsArray.addAll(
            QuizQuestions.initOptionsObjects(screen, currentQuestion)
        )

        goArray.add(frog)
        goArray.add(title)

        for (option in optionsArray) {
            goArray.add(option)
        }
    }

    override fun update(et: Float) {
        for (option in optionsArray) {
            if(frog.verifyAnswer(option)) {
                val questionPoints = answerPoints - errorCount

                if(option.isRightQuestion) {
                    soundPoolWrapper.dispose()

                    screen.scene = StartScene(
                        screen,
                        this.points + questionPoints,
                        currentQuestion == QuizQuestions.getAmountOfQuestions() - 1,
                        currentQuestion + 1,
                    )
                } else {
                    soundPoolWrapper.play(1)

                    errorCount += 1
                }
            }
        }

        for (go in goArray) {
            go.update(et)
        }
    }

    override fun render(canvas: Canvas) {
        val background = backgroundBitmap ?: return

        val src = Rect(128, 128, 512, 768)
        val dst = Rect(0, 0, screen.width, screen.height)

        canvas.drawBitmap(background, src, dst, null)

        for (go in goArray) {
            go.render(canvas)
        }
    }

    override fun onTouch(e: MotionEvent): Boolean {
        for (go in goArray) {
            go.handleEvent(e.action, e.x, e.y)
        }

        return true
    }

    override fun onResume() {}

    override fun onPause() {
        soundPoolWrapper.dispose()
    }
}