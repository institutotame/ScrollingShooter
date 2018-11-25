package com.atinem.scrollingshooter.components

import com.atinem.scrollingshooter.Transform
import kotlin.random.Random

class AlienVerticalSpawnComponent:  SpawnComponent {
    override fun spawn(playerTransform: Transform, transform: Transform) {
        val xPosition = Random.nextInt(transform.mScreenSize.x.toInt())
        val spawnHeight = Random.nextInt(300) - transform.mObjectHeight

        transform.setLocation(xPosition.toFloat(), spawnHeight)

        transform.headDown()

    }

}