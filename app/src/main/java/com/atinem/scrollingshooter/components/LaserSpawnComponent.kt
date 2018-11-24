package com.atinem.scrollingshooter.components

import com.atinem.scrollingshooter.Transform

class LaserSpawnComponent : SpawnComponent {
    override fun spawn(playerTransform: Transform, transform: Transform) {
        val startPosition = playerTransform.getFiringLocation(transform.getSize().x)

        transform.setLocation(startPosition.x, startPosition.y)
        if(playerTransform.mFacingRight){
            transform.headRight()
        }else{
            transform.headLeft()
        }
    }
}