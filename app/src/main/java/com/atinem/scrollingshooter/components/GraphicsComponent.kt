package com.atinem.scrollingshooter.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import com.atinem.scrollingshooter.Transform
import com.atinem.scrollingshooter.specs.ObjectSpec

interface GraphicsComponent {
    fun initialize(context : Context, spec : ObjectSpec, screenSize : PointF)

    fun draw(canvas : Canvas,  paint: Paint, transform : Transform)
}