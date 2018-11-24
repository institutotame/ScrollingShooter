package com.atinem.scrollingshooter.components

import android.graphics.Rect
import android.view.MotionEvent
import com.atinem.scrollingshooter.*

class PlayerInputComponent(gameEngine: GameEngine) : InputComponent, InputObserver {

    val mGameEngine : GameEngine
    var mTransform : Transform? = null

    init {
        gameEngine.addObserver(this)
        mGameEngine = gameEngine
    }

    override fun setTransform(transform: Transform) {
        mTransform = transform
    }

    override fun handleInput(event: MotionEvent?, gameState: GameState, controls: List<Rect>) {
        event?.let {
            mTransform?.let {letTransform ->
                val x = event.getX(event.actionIndex).toInt()
                val y = event?.getY(event.actionIndex).toInt()

                when(event.action and MotionEvent.ACTION_MASK){
                    MotionEvent.ACTION_UP -> {
                        if(controls[HUD.UP].contains(x,y) || controls[HUD.DOWN].contains(x,y)){
                            letTransform.stopVertical()
                        }
                    }
                    MotionEvent.ACTION_DOWN ->{
                       buttonClicked(controls,x,y,letTransform)
                    }
                    MotionEvent.ACTION_POINTER_UP -> {
                        if(controls[HUD.UP].contains(x,y) || controls[HUD.DOWN].contains(x,y)){
                            letTransform.stopVertical()
                        }
                    }
                    MotionEvent.ACTION_POINTER_DOWN ->{
                        buttonClicked(controls,x,y,letTransform)
                    }
                }
            }
        }
    }

    private fun buttonClicked(controls: List<Rect>, x: Int, y: Int, transform: Transform){
        when {
            controls[HUD.UP].contains(x,y) -> transform.headUp()
            controls[HUD.DOWN].contains(x,y) -> transform.headDown()
            controls[HUD.FLIP].contains(x,y) -> transform.flip()
            controls[HUD.SHOOT].contains(x,y) -> mGameEngine.spawnPlayerLaser(transform)
            else -> {

            }
        }
    }

}