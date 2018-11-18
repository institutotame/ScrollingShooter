package com.atinem.scrollingshooter

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    lateinit var mGameEngine : GameEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val display = windowManager.defaultDisplay

        val size = Point()
        display.getSize(size)
        mGameEngine = GameEngine(this, size)
        setContentView(mGameEngine)
    }

    override fun onResume() {
        super.onResume()
        mGameEngine.startJob()
    }

    override fun onPause() {
        super.onPause()
        mGameEngine.stopJob()
    }
}
