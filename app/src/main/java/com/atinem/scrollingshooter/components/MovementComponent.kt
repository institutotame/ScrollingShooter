package com.atinem.scrollingshooter.components

import com.atinem.scrollingshooter.Transform

interface MovementComponent {
    fun move(fps : Long, transform : Transform, playerTransform : Transform) : Boolean
}