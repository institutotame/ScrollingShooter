package com.atinem.scrollingshooter

class PhysicsEngine{
    fun update(fps : Long, particleSystem: ParticleSystem) : Boolean {
        if(particleSystem.mIsRunning){
            particleSystem.update(fps)
        }

        return false
    }
}