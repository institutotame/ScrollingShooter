package com.atinem.scrollingshooter.specs

import android.graphics.PointF

class AlienLaserSpec : ObjectSpec("Alien Laser", "alien_laser", .75f, PointF(14f,160f), arrayOf("StdGraphicsComponent", "LaserMovementComponent", "LaserSpawnComponent")) {
}