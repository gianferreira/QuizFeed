package com.example.quizfeed

import android.content.res.AssetFileDescriptor
import android.media.AudioAttributes
import android.media.SoundPool

class SoundPoolWrapper(descriptors: Array<AssetFileDescriptor>) {
    private var soundPool: SoundPool
    private var idStreams: MutableList<Int> = mutableListOf()

    init {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(10)
            .setAudioAttributes(audioAttributes)
            .build()

        for (descriptor in descriptors) {
            idStreams.add(
                soundPool.load(
                    descriptor,
                    10 - descriptors.indexOf(descriptor),
                ),
            )
        }

        soundPool.setOnLoadCompleteListener {
            sp, _, _ -> sp.play(
                idStreams.first(),
                1f,
                1f,
                10,
                -1,
                1f,
            )
        }
    }

    fun play(index: Int) {
        soundPool.play(
            idStreams[index],
            0.5f,
            0.5f,
            8,
            0,
            1f,
        )
    }

    fun dispose() {
        soundPool.autoPause()
        soundPool.release()
    }
}