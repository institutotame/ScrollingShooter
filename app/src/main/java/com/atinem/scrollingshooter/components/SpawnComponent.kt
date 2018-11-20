package com.atinem.scrollingshooter.components

import com.atinem.scrollingshooter.Transform

interface SpawnComponent {
    fun spawn(playerTransform : Transform, transform : Transform)
}