package com.atinem.scrollingshooter.components

import com.atinem.scrollingshooter.Transform

class PlayerSpawnComponent : SpawnComponent {
    override fun spawn(playerTransform: Transform, transform: Transform) {
        transform.setLocation(transform.mScreenSize.x / 2, transform.mScreenSize.y / 2)
    }

}