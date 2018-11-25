package com.atinem.scrollingshooter

import android.content.Context
import android.graphics.PointF
import com.atinem.scrollingshooter.specs.*

class Level(context: Context, screenSize: PointF, gameEngine: GameEngine) {
    companion object {
        val BACKGROUND_INDEX: Int = 0
        val PLAYER_INDEX: Int = 1
        val FIRST_PLAYER_LASER: Int = 2
        val LAST_PLAYER_LASER: Int = 4
        var mNextPlayerLaser: Int = -1
        val FIRST_ALIEN: Int = 5
        val SECOND_ALIEN: Int = 6
        val THIRD_ALIEN: Int = 7
        val FOURTH_ALIEN: Int = 8
        val FIFTH_ALIEN: Int = 9
        val SIXTH_ALIEN: Int = 10
        val LAST_ALIEN: Int = 11
        val FIRST_ALIEN_LASER = 12
        val LAST_ALIEN_LASER = 15
        var mNextAlienLaser: Int = -2
    }

    val objects = mutableListOf<GameObject>()

    init {
        val factory = GameObjectFactory(context,screenSize,gameEngine)
        buildGameObjects(factory)
    }

    fun buildGameObjects(factory: GameObjectFactory): List<GameObject>{
        objects.clear()
        objects.add(BACKGROUND_INDEX,factory.create(BackgroundSpec()))
        objects.add(PLAYER_INDEX, factory.create(PlayerSpec()))

        for(i in FIRST_PLAYER_LASER..LAST_PLAYER_LASER+1){
            objects.add(i,factory.create(PlayerLaserSpec()))
        }

        mNextPlayerLaser = FIRST_PLAYER_LASER

        // Create some aliens
        objects.add(FIRST_ALIEN, factory.create(AlienChaseSpec()))
        objects.add(SECOND_ALIEN, factory.create(AlienPatrolSpec()))
        objects.add(THIRD_ALIEN, factory.create(AlienPatrolSpec()))
        objects.add(FOURTH_ALIEN, factory.create(AlienChaseSpec()))
        objects.add(FIFTH_ALIEN, factory.create(AlienDiverSpec()))
        objects.add(SIXTH_ALIEN, factory.create(AlienDiverSpec()))


        // Create some aliens lasers
        for(i in FIRST_ALIEN_LASER..LAST_ALIEN_LASER){
            objects.add(i,factory.create(AlienLaserSpec()))
        }
        mNextAlienLaser = FIRST_ALIEN_LASER

        return objects
    }
}