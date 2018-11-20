package com.atinem.scrollingshooter.components

import com.atinem.scrollingshooter.Transform

interface PlayerLaserSpawner {
    fun spawnPlayerLaser(transform : Transform) : Boolean
}