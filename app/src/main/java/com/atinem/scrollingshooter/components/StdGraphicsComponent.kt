package com.atinem.scrollingshooter.components

import android.content.Context
import android.graphics.*
import com.atinem.scrollingshooter.Transform
import com.atinem.scrollingshooter.specs.ObjectSpec

class StdGraphicsComponent : GraphicsComponent {

    var mBitmap : Bitmap? = null
    var mBitmapReversed : Bitmap? = null

    override fun initialize(context: Context, spec: ObjectSpec, screenSize: PointF) {
        val resID = context.resources.getIdentifier(spec.mBitmapName, "drawable", context.packageName)

        mBitmap = BitmapFactory.decodeResource(context.resources, resID)

        mBitmap = Bitmap.createScaledBitmap(mBitmap,screenSize.x.toInt(),screenSize.y.toInt(),false)

        val matrix = Matrix()
        matrix.setScale(-1f,1f)
        mBitmap?.let {
            mBitmapReversed = Bitmap.createBitmap(it,0,0,it.width, it.height, matrix, true)
        }
    }

    override fun draw(canvas: Canvas, paint: Paint, transform: Transform) {
        if(transform.mFacingRight){
            canvas.drawBitmap(mBitmap,transform.mLocation.x, transform.mLocation.y, paint)
        }else{
            canvas.drawBitmap(mBitmapReversed,transform.mLocation.x,transform.mLocation.y, paint)
        }
    }
}