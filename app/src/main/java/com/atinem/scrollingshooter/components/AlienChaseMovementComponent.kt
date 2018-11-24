package com.atinem.scrollingshooter.components

import com.atinem.scrollingshooter.Transform
import kotlin.random.Random

class AlienChaseMovementComponent(val alienLaserSpawner: AlienLaserSpawner) : MovementComponent{
    override fun move(fps: Long, transform: Transform, playerTransform: Transform): Boolean {
        val TAKE_SHOT = 0
        val SHOT_CHANCE = 100

        val screenWidth = transform.mScreenSize.x
        val playerLocation = playerTransform.mLocation

        val height = transform.mObjectHeight
        val facingRight = transform.mFacingRight

        val mChasingDistance = transform.mScreenSize.x / 3f
        val mSeeingDistance = transform.mScreenSize.x / 1.5f

        val location = transform.mLocation
        val speed = transform.mSpeed

        val verticalSpeedDifference = .3f
        val slowDownRelativeToPlayer = 1.8f

        val verticalSearchBounce = 20f

        if(Math.abs(location.x - playerLocation.x) > mChasingDistance){
            if(location.x < playerLocation.x){
                transform.headRight()
            }else if(location.x > playerLocation.x){
                transform.headLeft()
            }
        }
        if(Math.abs(location.x - playerLocation.x) <= mSeeingDistance){
            if(location.y - playerLocation.y < -verticalSearchBounce){
                transform.headDown()
            }else if(location.y - playerLocation.y > verticalSearchBounce){
                transform.headUp()
            }
            if(!playerTransform.mFacingRight){
                location.x += speed * slowDownRelativeToPlayer / fps
            }else{
                location.x -= speed * slowDownRelativeToPlayer / fps
            }
        }else{
            transform.stopVertical()
        }
        if(transform.mHeadingDown){
            location.y += speed * verticalSpeedDifference / fps
        }else if(transform.mHeadingUp){
            location.y -= speed * verticalSpeedDifference / fps
        }
        if(transform.mHeadingLeft){
            location.x -= speed/fps
        }
        if(transform.mFacingRight){
            location.x += speed/fps
        }

        transform.updateCollider()
        if(Random.nextInt(SHOT_CHANCE) == TAKE_SHOT){
            if(Math.abs(playerLocation.y - location.y) < height){
                if((facingRight && playerLocation.x > location.x || !facingRight && playerLocation.x < location.x) && Math.abs(playerLocation.x-location.x) < screenWidth){
                    alienLaserSpawner.spawnAlienLaser(transform)
                }
            }
        }
        return true
    }
}