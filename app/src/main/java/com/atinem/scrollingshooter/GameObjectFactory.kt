package com.atinem.scrollingshooter

import android.content.Context
import android.graphics.PointF
import com.atinem.scrollingshooter.specs.ObjectSpec

class GameObjectFactory(val context : Context, val screenSize: PointF, val gameEngine: GameEngine) {
    fun create(spec: ObjectSpec): GameObject{
        val gameObject = GameObject()

        val numComponents = spec.mComponents.size
        val HIDDEN = -2000f
        gameObject.mTag = spec.mTag

        val speed = screenSize.x / spec.mSpeed
        val objectSize = PointF(screenSize.x / spec.mSizeScale.x, screenSize.y / spec.mSizeScale.y)

        val location = PointF(HIDDEN,HIDDEN)
        gameObject.mTransform = Transform(speed,objectSize.x,objectSize.y,location,screenSize)


        //TODO Page 562
        return gameObject
    }
}