package com.atinem.scrollingshooter

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView

class Renderer(surfaceView : SurfaceView) {

    private val mSurfaceHolder : SurfaceHolder = surfaceView.holder
    private val mPaint = Paint()

    fun draw(objects: List<GameObject>, gameState: GameState, hud: HUD, particleSystem: ParticleSystem){
        if(mSurfaceHolder.surface.isValid){
            val canvas = mSurfaceHolder.lockCanvas()
            canvas.drawColor(Color.argb(255,0,0,0))

            if(gameState.mDrawing){
                for(gameObject in objects){
                    if(gameObject.isActive){
                        gameObject.draw(canvas,mPaint)
                    }
                }
            }

            if(gameState.mGameOver){
                objects[Level.BACKGROUND_INDEX].draw(canvas,mPaint)
            }

            if(particleSystem.mIsRunning){
                particleSystem.draw(canvas,mPaint)
            }

            hud.draw(canvas,mPaint,gameState)

            mSurfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

}