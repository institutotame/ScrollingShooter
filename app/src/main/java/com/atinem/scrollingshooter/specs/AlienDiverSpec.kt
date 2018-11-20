package com.atinem.scrollingshooter.specs

import android.graphics.PointF

class AlienDiverSpec : ObjectSpec("Alien", "alien_ship3", 4f, PointF(60f, 30f), arrayOf("StdGraphicsComponent", "AlienDiverMovementComponent", "AlienVerticalSpawnComponent"))