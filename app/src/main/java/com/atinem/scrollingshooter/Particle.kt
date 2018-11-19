package com.atinem.scrollingshooter

import android.graphics.PointF

class Particle(direction : PointF) {

    val mVelocity = PointF()
    val mPosition = PointF()

    init {
        mVelocity.x = direction.x
        mVelocity.y = direction.y
    }

    fun update(){
        mPosition.x += mVelocity.x
        mPosition.y += mVelocity.y
    }

    fun setPosition(position : PointF){
        mPosition.x = position.x
        mPosition.y = position.y
    }

}