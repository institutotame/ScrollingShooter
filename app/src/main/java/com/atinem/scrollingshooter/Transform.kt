package com.atinem.scrollingshooter

import android.graphics.PointF
import android.graphics.RectF

class Transform(speed : Float, objectWidth : Float, objectHeight : Float, startingLocation : PointF, val screenSize : PointF) {



    var mXCLip : Int = 0
    var mReversedFirst : Boolean = false

    val mCollider = RectF()
    var mLocation = startingLocation
        private set
    var mFacingRight : Boolean = true
        private set
    var mHeadingUp : Boolean = false
        private set
    var mHeadingDown : Boolean = false
        private set
    var mHeadingLeft : Boolean = false
        private set
    var mHeadingRight : Boolean = false
        private set

    var mSpeed = speed
        private set

    val mObjectHeight = objectHeight
    val mObjectWidth = objectWidth

    val mScreenSize : PointF = screenSize

    fun flipReversedFirst(){
        mReversedFirst = !mReversedFirst
    }

    fun headUp(){
        mHeadingUp = true
        mHeadingDown = false
    }

    fun headDown(){
        mHeadingDown = true
        mHeadingUp = false
    }

    fun headRight() {
        mHeadingRight = true
        mHeadingLeft = false
        mFacingRight = true
    }

    fun headLeft(){
        mHeadingLeft = true
        mHeadingRight = false
        mFacingRight = false
    }

    fun updateCollider(){
        mCollider.top = mLocation.y + (mObjectHeight / 10)
        mCollider.left = mLocation.x + (mObjectWidth / 10)
        mCollider.bottom = (mCollider.top + mObjectHeight) - mObjectHeight/10
        mCollider.right = (mCollider.left + mObjectWidth) - mObjectWidth/10
    }

    fun stopVertical(){
        mHeadingUp = false
        mHeadingDown = false
    }

    fun setLocation(horizontal : Float, vertical : Float){
        mLocation = PointF(horizontal, vertical)
        updateCollider()
    }

    fun getSize() : PointF{
        return PointF(mObjectWidth, mObjectHeight)
    }

    fun flip(){
        mFacingRight = !mFacingRight
    }

    fun getFiringLocation(laserLength : Float) : PointF{
        val mFiringLocation = PointF()

        if(mFacingRight){
            mFiringLocation.x = mLocation.x + (mObjectWidth/8f)
        }else{
            mFiringLocation.x = mLocation.x + (mObjectWidth/8f) - laserLength
        }

        mFiringLocation.y = mLocation.y + (mObjectHeight/1.28f)
        return mFiringLocation
    }
}