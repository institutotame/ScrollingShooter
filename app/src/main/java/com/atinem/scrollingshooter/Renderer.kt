package com.atinem.scrollingshooter

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView

class Renderer(surfaceView : SurfaceView) {

    private val mSurfaceHolder : SurfaceHolder = surfaceView.holder
    private val mPaint = Paint()

    fun draw(gameState: GameState, hud: HUD){
        if(mSurfaceHolder.surface.isValid){
            val canvas = mSurfaceHolder.lockCanvas()
            canvas.drawColor(Color.argb(255,0,0,0))

            if(gameState.mDrawing){
                // Draw all game objects here
            }

            if(gameState.mGameOver){
                // Draw a background graphic here
            }

            // Draw a particle explosion system here

            hud.draw(canvas,mPaint,gameState)

            mSurfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

}