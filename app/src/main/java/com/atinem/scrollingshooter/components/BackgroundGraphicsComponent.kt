package com.atinem.scrollingshooter.components

import android.content.Context
import android.graphics.*
import com.atinem.scrollingshooter.Transform
import com.atinem.scrollingshooter.specs.ObjectSpec

class BackgroundGraphicsComponent : GraphicsComponent {

    var mBitmap : Bitmap? = null
    var mBitmapReversed: Bitmap? = null

    override fun initialize(context: Context, spec: ObjectSpec, screenSize: PointF) {
        val resID = context.resources.getIdentifier(spec.mBitmapName, "drawable", context.packageName)
        mBitmap = BitmapFactory.decodeResource(context.resources, resID)
        mBitmap?.let {letBitmap->
            mBitmap = Bitmap.createScaledBitmap(letBitmap, screenSize.x.toInt(), screenSize.y.toInt(), false)
            val matrix = Matrix()
            matrix.setScale(-1f, 1f)
            mBitmapReversed = Bitmap.createBitmap(letBitmap,0,0,letBitmap.width, letBitmap.height, matrix, true)
        }
    }

    override fun draw(canvas: Canvas, paint: Paint, transform: Transform) {
        mBitmap?.let {
            mBitmapReversed?.let {bitmapReversed ->
                val xClip = transform.mXCLip
                val width = it.width
                val height = it.height
                val startY = 0
                val endY = transform.mScreenSize.y.toInt() + 20

                val fromRect1 = Rect(0, 0, width - xClip, height)
                val toRect1 = Rect(xClip, startY, width, endY)

                val fromRect2 = Rect(width - xClip, 0, width, height)
                val toRect2 = Rect(0, startY, xClip, endY)

                if (!transform.mReversedFirst) {
                    canvas.drawBitmap(it, fromRect1, toRect1, paint)
                    canvas.drawBitmap(bitmapReversed, fromRect2, toRect2, paint)
                }else{
                    canvas.drawBitmap(it,fromRect2,toRect2,paint)
                    canvas.drawBitmap(bitmapReversed,fromRect1,toRect1,paint)
                }
            }
        }
    }
}