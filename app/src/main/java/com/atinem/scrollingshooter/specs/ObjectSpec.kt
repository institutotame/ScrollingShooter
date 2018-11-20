package com.atinem.scrollingshooter.specs

import android.graphics.PointF

abstract class ObjectSpec(tag : String, bitmapName : String, speed : Float, relativeScale : PointF, components : Array<String>) {
    val mTag : String = tag
    val mBitmapName : String = bitmapName
    val mSpeed : Float = speed
    val mSizeScale : PointF = relativeScale
    val mComponents : Array<String> = components
}