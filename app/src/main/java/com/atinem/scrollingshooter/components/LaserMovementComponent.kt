package com.atinem.scrollingshooter.components

import com.atinem.scrollingshooter.Transform

class LaserMovementComponent : MovementComponent {
    override fun move(fps: Long, transform: Transform, playerTransform: Transform): Boolean {
        val range = transform.mScreenSize.x * 2

        val location = transform.mLocation
        val speed = transform.mSpeed

        if(transform.mHeadingRight){
            location.x += speed / fps
        }else if(transform.mHeadingLeft){
            location.x -= speed / fps
        }

        if(location.x < -range || location.x > range){
            return false
        }

        transform.updateCollider()
        return true
    }
}