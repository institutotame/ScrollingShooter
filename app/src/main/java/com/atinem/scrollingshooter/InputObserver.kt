package com.atinem.scrollingshooter

import android.graphics.Rect
import android.view.MotionEvent

interface InputObserver {
    fun handleInput(event : MotionEvent?, gameState: GameState, controls : List<Rect>)
}