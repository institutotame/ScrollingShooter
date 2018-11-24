package com.atinem.scrollingshooter.components

import com.atinem.scrollingshooter.Transform
import kotlin.random.Random

class AlienHorizontalSpawnComponent: SpawnComponent {
    override fun spawn(playerTransform: Transform, transform: Transform) {
        val screenSize = transform.mScreenSize

        val left = Random.nextBoolean()

        val distance = Random.nextInt(2000) + transform.mScreenSize.x

        val spawnHeight = Random.nextFloat() * screenSize.y - transform.getSize().y

        if(left){
            transform.setLocation(-distance, spawnHeight)
            transform.headRight()
        }else{
            transform.setLocation(distance, spawnHeight)
            transform.headLeft()
        }
    }
}