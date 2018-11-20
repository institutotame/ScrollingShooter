package com.atinem.scrollingshooter.specs

import android.graphics.PointF

class AlienPatrolSpec : ObjectSpec("Alien", "alien_ship2", 5f, PointF(15f,15f), arrayOf("StdGraphicsComponent", "AlienPatrolMovementComponent", "AlienHorizontalSpawnComponent"))