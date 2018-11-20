package com.atinem.scrollingshooter.specs

import android.graphics.PointF

class PlayerSpec : ObjectSpec("Player", "player_ship", 1f, PointF(15f,15f), arrayOf("PlayerInputComponent", "StdGraphicsComponent", "PlayerMovementComponent", "PlayerSpawnComponent"))