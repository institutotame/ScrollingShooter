package com.atinem.scrollingshooter.components

import com.atinem.scrollingshooter.Transform

class BackgroundMovementComponent : MovementComponent{
    override fun move(fps: Long, transform: Transform, playerTransform: Transform): Boolean {
        var currentXClip = transform.mXCLip

        if(playerTransform.mFacingRight){
            currentXClip -= (transform.mSpeed / fps).toInt()
            transform.mXCLip = currentXClip
        }else{
            currentXClip += (transform.mSpeed / fps).toInt()
            transform.mXCLip = currentXClip
        }

        if(currentXClip >= transform.getSize().x){
            transform.mXCLip = 0
            transform.flipReversedFirst()
        }else if(currentXClip <=0){
            transform.mXCLip = transform.getSize().x.toInt()
            transform.flipReversedFirst()
        }
        return true
    }
}