package com.atinem.scrollingshooter

import android.graphics.Rect
import android.view.MotionEvent

class UIController(gameEngineBroadcaster : GameEngineBroadcaster) : InputObserver {
    init {
        gameEngineBroadcaster.addObserver(this)
    }

    override fun handleInput(event: MotionEvent?, gameState: GameState, buttons: List<Rect>) {
        val i = event?.actionIndex
        val x = event?.x?.toInt()
        val y = event?.y?.toInt()

        val eventType = event?.action ?.and(MotionEvent.ACTION_MASK)

        if(eventType == MotionEvent.ACTION_UP || eventType == MotionEvent.ACTION_POINTER_UP){
            if(x!=null && y!=null){
                if(buttons[HUD.PAUSE].contains(x,y)) {
                    if(!gameState.mPaused){
                        gameState.pause()
                    }else if(gameState.mGameOver){
                        gameState.startNewGame()
                    }else if(gameState.mPaused && !gameState.mGameOver){
                        gameState.resume()
                    }
                }
            }
        }
    }
}