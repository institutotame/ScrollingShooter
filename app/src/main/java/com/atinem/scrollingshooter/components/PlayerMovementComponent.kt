package com.atinem.scrollingshooter.components

import com.atinem.scrollingshooter.Transform

class PlayerMovementComponent : MovementComponent {
    override fun move(fps: Long, transform: Transform, playerTransform: Transform): Boolean {
        val screenHeight = transform.mScreenSize.y

        val location = transform.mLocation
        val speed = transform.mSpeed
        val height = transform.mObjectHeight

        if(transform.mHeadingDown){
            location.y += speed / fps
        }else if(transform.mHeadingUp){
            location.y -= speed / fps
        }

        if(location.y > screenHeight - height){
            location.y = screenHeight - height
        }else if(location.y < 0){
            location.y = 0f
        }

        transform.updateCollider()
        return true
    }
}