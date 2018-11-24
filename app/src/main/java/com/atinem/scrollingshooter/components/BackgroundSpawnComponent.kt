package com.atinem.scrollingshooter.components

import com.atinem.scrollingshooter.Transform

class BackgroundSpawnComponent : SpawnComponent {
    override fun spawn(playerTransform: Transform, transform: Transform) {
        transform.setLocation(0f,0f)
    }
}