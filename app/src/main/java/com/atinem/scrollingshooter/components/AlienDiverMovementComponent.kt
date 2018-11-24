package com.atinem.scrollingshooter.components

import com.atinem.scrollingshooter.Transform
import kotlin.random.Random

class AlienDiverMovementComponent: MovementComponent {
    override fun move(fps: Long, transform: Transform, playerTransform: Transform): Boolean {
        val location = transform.mLocation
        val speed = transform.mSpeed

        val slowDownRelativeToPlayer = 1.8f

        if(!playerTransform.mFacingRight){
            location.x += speed * slowDownRelativeToPlayer / fps
        }else{
            location.x -= speed * slowDownRelativeToPlayer / fps
        }

        location.y += speed / fps

        if(location.y > transform.mScreenSize.y){
            location.y = Random.nextInt(300) - transform.mObjectHeight
            location.x = Random.nextInt(transform.mScreenSize.x.toInt()).toFloat()
        }

        transform.updateCollider()

        return true
    }
}