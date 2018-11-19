package com.atinem.scrollingshooter

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import kotlin.random.Random

class ParticleSystem {
    var mDuration : Float = 0f

    val mParticles = mutableListOf<Particle>()
    var mIsRunning = false

    fun init(numParticles : Int){
        mParticles.clear()

        for(i in 0 until numParticles){
            val angle = Random.nextInt(360).toFloat() * 3.14f / 180f
            val speed = Random.nextInt(1, 20).toFloat()

            val direction = PointF((Math.cos(angle.toDouble()) * speed).toFloat(), (Math.sin(angle.toDouble()) * speed).toFloat())

            mParticles.add(Particle(direction))
        }
    }

    fun update(fps : Long){
        mDuration -= 1f/fps

        for(particle in mParticles){
            particle.update()
        }

        if(mDuration < 0){
            mIsRunning = false
        }
    }

    fun emitParticles(startPosition : PointF){
        mIsRunning = true
        mDuration = 1f

        for(particles in mParticles){
            particles.setPosition(startPosition)
        }
    }

    fun draw(canvas: Canvas, paint: Paint){
        for(particle in mParticles){
            paint.setARGB(255, Random.nextInt(255), Random.nextInt(255), Random.nextInt(255))
            canvas.drawRect(particle.mPosition.x, particle.mPosition.y, particle.mPosition.x+25, particle.mPosition.y+25, paint)
        }
    }
}