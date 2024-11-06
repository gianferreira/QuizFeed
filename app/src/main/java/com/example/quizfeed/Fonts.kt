package com.example.quizfeed

import android.content.Context
import android.graphics.Typeface
import android.util.Log

object Fonts {

    lateinit var bangers: Typeface

    fun initializeFont(context: Context) {
        try {
            bangers = Typeface.createFromAsset(context.assets, "Bangers-Regular.ttf")
        }
        catch (e: Exception) {
            Log.d("App", "Algo ocorreu de errado ao carregar a fonte")
        }
    }
}