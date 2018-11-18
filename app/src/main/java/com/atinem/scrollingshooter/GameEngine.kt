package com.atinem.scrollingshooter

import android.content.Context
import android.graphics.Point
import android.view.MotionEvent
import android.view.SurfaceView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GameEngine(context : Context, size : Point) : SurfaceView(context), GameStarter {
    private var mFPS : Long = 0
    private var job : Job? = null
    private val mGameState = GameState(this, context)
    private val mSoundEngine = SoundEngine(context)
    private val mHUD = HUD(size)
    private val mRenderer = Renderer(this)


    fun runJob(){
        job = GlobalScope.launch {
            while(mGameState.mThreadRunning){
                val frameStartTime = System.currentTimeMillis()

                if(!mGameState.mPaused){
                    //Update all game objects here
                }
                //Draw all the game objects here
                //in a new way
                mRenderer.draw(mGameState,mHUD)

                val timeThisFrame = System.currentTimeMillis() - frameStartTime
                if(timeThisFrame >= 1){
                    val MILLIS_IN_SECOND = 1000
                    mFPS = MILLIS_IN_SECOND / timeThisFrame
                }
            }

        }
    }

    fun startJob(){
        mGameState.startThread()
        runJob()
    }

    fun stopJob(){
        mGameState.stopEverything()
        job?.cancel()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //Handle the player's input here

        mSoundEngine.playShoot()

        return super.onTouchEvent(event)
    }

    override fun deSpawnwReSpawn() {
        // Eventually this will despawn
        // and then respawn all the game objects
    }
}