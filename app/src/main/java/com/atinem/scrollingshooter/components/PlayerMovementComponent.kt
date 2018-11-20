package com.atinem.scrollingshooter.components

import com.atinem.scrollingshooter.Transform

class PlayerMovementComponent : MovementComponent {
    override fun move(fps: Long, transform: Transform, playerTransform: Transform): Boolean {
        return true
    }
}