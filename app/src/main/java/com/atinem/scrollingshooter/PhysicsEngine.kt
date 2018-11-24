package com.atinem.scrollingshooter

class PhysicsEngine{
    fun update(fps : Long, objects: List<GameObject>, gameSetate: GameState, soundEngine: SoundEngine, particleSystem: ParticleSystem) : Boolean {

        for(gameObject in objects){
            if(gameObject.isActive){
                gameObject.update(fps,objects[Level.PLAYER_INDEX].mTransform)
            }
        }

        if(particleSystem.mIsRunning){
            particleSystem.update(fps)
        }

        return false
    }
}