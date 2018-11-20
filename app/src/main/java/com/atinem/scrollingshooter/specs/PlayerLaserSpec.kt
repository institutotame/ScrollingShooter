package com.atinem.scrollingshooter.specs

import android.graphics.PointF
import com.atinem.scrollingshooter.specs.ObjectSpec

class PlayerLaserSpec : ObjectSpec("Player Laser", "player_laser", .65f, PointF(8f,160f), arrayOf("StdGraphicsComponent", "LaserMovementComponent", "LaserSpawnComponent")) {
}