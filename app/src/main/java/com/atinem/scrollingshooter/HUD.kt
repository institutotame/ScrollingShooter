package com.atinem.scrollingshooter

import android.graphics.*

class HUD(size : Point) {

    companion object {
        val UP = 0
        val DOWN = 1
        val FLIP = 2
        val SHOOT = 3
        val PAUSE = 4
    }


    private val mScreenHeight : Int = size.y
    private val mScreenWidth : Int = size.x
    private val mTextFormatting : Float = size.x / 50f

    val controls : List<Rect>

    init {
        val buttonWidth = mScreenWidth / 14
        val buttonHeight = mScreenHeight / 12
        val buttonPadding = mScreenWidth / 90

        val up = Rect(
            buttonPadding,
            mScreenHeight - (buttonHeight*2) - buttonPadding*2,
            buttonWidth + buttonPadding, mScreenHeight - buttonHeight - buttonPadding*2)

        val down = Rect(
            buttonPadding,
            mScreenHeight - buttonHeight - buttonPadding,
            buttonWidth + buttonPadding,
            mScreenHeight - buttonPadding)

        val flip = Rect(
            mScreenWidth - buttonPadding - buttonWidth,
            mScreenHeight - buttonHeight - buttonPadding,
            mScreenWidth - buttonPadding,
            mScreenHeight - buttonPadding
        )

        val shoot = Rect(
            mScreenWidth - buttonPadding - buttonWidth,
            mScreenHeight - buttonHeight*2 - buttonPadding*2,
            mScreenWidth - buttonPadding,
            mScreenHeight - buttonHeight - buttonPadding*2)

        val pause = Rect(
            mScreenWidth - buttonPadding - buttonWidth,
            buttonPadding,
            mScreenWidth - buttonPadding,
            buttonPadding + buttonHeight)

        controls = listOf(up,down,flip,shoot,pause)
    }

    fun draw(canvas : Canvas, paint : Paint, gameState : GameState){
        paint.color = Color.argb(255,255,255,255)
        paint.textSize = mTextFormatting

        canvas.drawText("Hi: ${gameState.mHighScore}", mTextFormatting, mTextFormatting, paint)
        canvas.drawText("Score: ${gameState.mScore}", mTextFormatting, mTextFormatting * 2, paint)
        canvas.drawText("Lives: ${gameState.mNumShips}", mTextFormatting, mTextFormatting * 3, paint)

        if(gameState.mGameOver){
            paint.textSize = mTextFormatting * 5
            canvas.drawText("PAUSED", mScreenWidth / 3f, mScreenHeight / 2f, paint)
        }

        drawControls(canvas, paint)
    }

    private fun drawControls(canvas : Canvas, paint : Paint){
        paint.color = Color.argb(100,255,255,255)

        controls.forEach {
            canvas.drawRect(it.left.toFloat(), it.top.toFloat(), it.right.toFloat(), it.bottom.toFloat(), paint)
        }

        paint.color = Color.argb(255,255,255,255)
    }

}