package com.atinem.scrollingshooter

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GameState(val gameStarter: GameStarter, val context : Context) {


    var mThreadRunning = false
        private set
    var mPaused = true
        private set
    var mGameOver = true
        private set
    var mDrawing = false
        private set

    private val mEditor : SharedPreferences.Editor

    var mScore : Int = 0
        private set
    var mHighScore : Int
        private set
    var mNumShips : Int = 0
        private set

    init {
        val prefs = context.getSharedPreferences("HiScore", Context.MODE_PRIVATE)
        mHighScore = prefs.getInt("hi_score", 0)
        mEditor =  prefs.edit()
    }

    fun startNewGame(){
        mScore = 0
        mNumShips = 3
        stopDrawing()
        gameStarter.deSpawnwReSpawn()
        resume()

        startDrawing()
    }

    private fun endGame(){
        mGameOver = true
        mPaused = true
        if(mScore > mHighScore){
            mHighScore = mScore
            GlobalScope.launch(IO){
                // Save high score
                mEditor.putInt("hi_score", mHighScore)
                mEditor.commit()
            }

        }
    }

    fun loseLife(se : SoundEngine){
        mNumShips--
        se.playPlayerExplode()
        if(mNumShips == 0){
            pause()
            endGame()
        }
    }

    fun increaseScore(){
        mScore++
    }

    fun resume(){
        mGameOver = false
        mPaused = false
    }

    fun pause(){
        mPaused = true
    }

    fun stopEverything(){
        mPaused = true
        mGameOver = true
        mThreadRunning = false
    }

    fun startThread(){
        mThreadRunning = true
    }

    private fun stopDrawing(){
        mDrawing = false
    }

    private fun startDrawing(){
        mDrawing = true
    }
}