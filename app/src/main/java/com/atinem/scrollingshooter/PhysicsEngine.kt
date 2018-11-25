package com.atinem.scrollingshooter

import android.graphics.PointF
import android.graphics.RectF

class PhysicsEngine{
    fun update(fps : Long, objects: List<GameObject>, gameState: GameState, soundEngine: SoundEngine, particleSystem: ParticleSystem) : Boolean {

        for(gameObject in objects){
            if(gameObject.isActive){
                gameObject.update(fps,objects[Level.PLAYER_INDEX].mTransform)
            }
        }

        if(particleSystem.mIsRunning){
            particleSystem.update(fps)
        }

        return detectCollisions(gameState, objects, soundEngine, particleSystem)
    }

    private fun detectCollisions(gameState: GameState, objects: List<GameObject>, soundEngine: SoundEngine, particleSystem: ParticleSystem): Boolean  {
        var playerHit = false
        for(gameObject in objects){
            if(gameObject.isActive){
                for(gameObject2 in objects){
                    if(gameObject2.isActive){
                        if(RectF.intersects(gameObject.mTransform?.mCollider, gameObject2.mTransform?.mCollider)){
                            when("${gameObject.mTag} with ${gameObject2.mTag}"){
                                "Player with Alien Laser" -> {
                                    playerHit = true
                                    gameState.loseLife(soundEngine)
                                }
                                "Player with Alien" -> {
                                    playerHit = true
                                    gameState.loseLife(soundEngine)
                                }
                                "Player Laser with Alien" -> {
                                    gameState.increaseScore()

                                    gameObject2.mTransform?.let {letTransform ->
                                        particleSystem.emitParticles(PointF(letTransform.mLocation.x, letTransform.mLocation.y))
                                    }

                                    gameObject2.isActive = false
                                    gameObject2.spawn(objects[Level.PLAYER_INDEX].mTransform)

                                    gameObject.isActive = false
                                    soundEngine.playAlienExplode()
                                }
                            }
                        }
                    }
                }
            }
        }
        return playerHit
    }
}