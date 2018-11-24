package com.atinem.scrollingshooter.components

import com.atinem.scrollingshooter.Transform
import kotlin.random.Random

class AlienPatrolMovementComponent(val alienLaserSpawner: AlienLaserSpawner) : MovementComponent {

    override fun move(fps: Long, transform: Transform, playerTransform: Transform): Boolean {
        val TAKE_SHOT = 0
        val SHOT_CHANCE = 100

        val playerLocation = playerTransform.mLocation

        val MIN_VERTICAL_BOUNDS = 0
        val screenX = transform.mScreenSize.x
        val screeny = transform.mScreenSize.y

        val mSeeingDistance = screenX * .5f

        val loc = transform.mLocation
        val speed = transform.mSpeed
        val height = transform.mObjectHeight

        val MAX_VERTICAL_BOUNDS = screeny - height
        val MAX_HORIZONTAL_BOUNDS = 2 * screenX
        val MIN_HORIZONTAL_BOUNDS = 2 * -screenX

        var horizontalSpeedAdjustmentRelativeToPlayer = 0f
        val horizontalSpeedAdjustmentModifier = .8f

        if(Math.abs(loc.x - playerLocation.x) < mSeeingDistance){
            if(playerTransform.mFacingRight != transform.mFacingRight){
                horizontalSpeedAdjustmentRelativeToPlayer = speed* horizontalSpeedAdjustmentModifier
            }else{
                horizontalSpeedAdjustmentRelativeToPlayer = -(speed * horizontalSpeedAdjustmentModifier)
            }
        }

        if(transform.mHeadingLeft){
            loc.x -= (speed + horizontalSpeedAdjustmentRelativeToPlayer) / fps

            if(loc.x < MIN_HORIZONTAL_BOUNDS){
                loc.x = MIN_HORIZONTAL_BOUNDS
                transform.headRight()
            }
        }else{
            loc.x += (speed + horizontalSpeedAdjustmentModifier) / fps

            if(loc.x > MAX_HORIZONTAL_BOUNDS){
                loc.x = MAX_HORIZONTAL_BOUNDS
                transform.headLeft()
            }
        }

        if(transform.mHeadingDown){
            loc.y += speed/fps
            if(loc.y > MAX_VERTICAL_BOUNDS){
                transform.headUp()
            }
        }else{
            loc.y -= speed / fps
            if(loc.y < MIN_VERTICAL_BOUNDS){
                transform.headDown()
            }
        }

        transform.updateCollider()

        if(Random.nextInt(SHOT_CHANCE) == TAKE_SHOT){
            if(Math.abs(playerLocation.y - loc.y) < height){
                if((transform.mFacingRight && playerLocation.x > loc.x || !transform.mFacingRight && playerLocation.x < loc.x) && Math.abs(playerLocation.x - loc.x) < screenX) {
                    alienLaserSpawner.spawnAlienLaser(transform)
                }
            }
        }

        return true
    }
}