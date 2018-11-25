package com.atinem.scrollingshooter

import android.content.Context
import android.graphics.PointF
import com.atinem.scrollingshooter.components.*
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


        for(i in 0 until numComponents){
            when(spec.mComponents[i]){
                "PlayerInputComponent" -> gameObject.setInput(PlayerInputComponent(gameEngine))
                "StdGraphicsComponent" -> gameObject.setGraphics(StdGraphicsComponent(), context, spec, objectSize)
                "PlayerMovementComponent" -> gameObject.movementComponent = PlayerMovementComponent()
                "LaserMovementComponent" -> gameObject.movementComponent = LaserMovementComponent()
                "PlayerSpawnComponent" -> gameObject.spawnComponent = PlayerSpawnComponent()
                "LaserSpawnComponent" -> gameObject.spawnComponent = LaserSpawnComponent()
                "BackgroundGraphicsComponent" -> gameObject.setGraphics(BackgroundGraphicsComponent(), context,spec,objectSize)
                "BackgroundMovementComponent" -> gameObject.movementComponent = BackgroundMovementComponent()
                "BackgroundSpawnComponent" -> gameObject.spawnComponent = BackgroundSpawnComponent()
                "AlienChaseMovementComponent" -> gameObject.movementComponent = AlienChaseMovementComponent(gameEngine)
                "AlienPatrolMovementComponent" -> gameObject.movementComponent = AlienPatrolMovementComponent(gameEngine)
                "AlienDiverMovementComponent" -> gameObject.movementComponent = AlienDiverMovementComponent()
                "AlienHorizontalSpawnComponent" -> gameObject.spawnComponent = AlienHorizontalSpawnComponent()
                "AlienVerticalSpawnComponent" -> gameObject.spawnComponent = AlienVerticalSpawnComponent()
            }
        }
        return gameObject
    }
}