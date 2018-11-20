package com.atinem.scrollingshooter.specs

import android.graphics.PointF

class AlienChaseSpec : ObjectSpec("Alien", "alien_ship1", 4f, PointF(15f, 15f), arrayOf("StdGraphicsComponent", "AlienChaseMovementComponent", "AlienHorizontalSpawnComponent"))