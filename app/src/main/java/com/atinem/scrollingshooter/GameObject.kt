package com.atinem.scrollingshooter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import com.atinem.scrollingshooter.components.GraphicsComponent
import com.atinem.scrollingshooter.components.InputComponent
import com.atinem.scrollingshooter.components.MovementComponent
import com.atinem.scrollingshooter.components.SpawnComponent
import com.atinem.scrollingshooter.specs.ObjectSpec
import java.security.AccessControlContext

class GameObject {
    var mTransform : Transform? = null
    private var isActive : Boolean = false
    var mTag : String? = null

    var graphicsComponent : GraphicsComponent? = null
    var movementComponent : MovementComponent? = null
    var spawnComponent : SpawnComponent? = null

    fun setGraphics(graphicsComponent: GraphicsComponent, context: Context, spec: ObjectSpec, objectSize: PointF){
        this.graphicsComponent = graphicsComponent
        graphicsComponent.initialize(context,spec,objectSize)
    }

    fun setInput(inputComponent: InputComponent){
        mTransform?.let {
            inputComponent.setTransform(it)
        }
    }

    fun draw(canvas: Canvas, paint: Paint){
        mTransform?.let {
            graphicsComponent?.draw(canvas,paint,it)
        }
    }

    fun update(fps: Long, playerTransform: Transform){
        mTransform?.let {
            if(movementComponent?.move(fps,it, playerTransform) == false){
                // Component returned false
                isActive = false
            }
        }

    }

    fun spawn(playerTransform: Transform) : Boolean {
        // Only spawnComponent if not already active
        mTransform?.let {
            if(!isActive){
                spawnComponent?.spawn(playerTransform, it)
                isActive = true
                return true
            }
        }
        return false
    }

}