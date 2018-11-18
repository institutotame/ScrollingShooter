package com.atinem.scrollingshooter

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException

class SoundEngine(context : Context) {
    // For playing sound effects
    private val mSP : SoundPool
    private var mShoot_ID : Int = -1
    private var mAlienExplodeID : Int = -1
    private var mPlayerExplodeID : Int = -1

    init {
        mSP = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            val attributes =
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
            SoundPool.Builder()
                .setMaxStreams(5)
                .setAudioAttributes(attributes)
                .build()
        }else{
            SoundPool(5, AudioManager.STREAM_MUSIC, 0)
        }
        GlobalScope.launch(IO){
            try{
                val assetManager = context.assets
                var descriptor : AssetFileDescriptor
                descriptor = assetManager.openFd("shoot.ogg")
                mShoot_ID = mSP.load(descriptor,0)

                descriptor = assetManager.openFd("alien_explosion.ogg")
                mAlienExplodeID = mSP.load(descriptor,0)

                descriptor = assetManager.openFd("player_explosion.ogg")
                mPlayerExplodeID = mSP.load(descriptor,0)
            }catch(e: IOException){
                e.printStackTrace()
            }
        }
    }

    fun playShoot(){
        mSP.play(mShoot_ID, 1f, 1f, 0,0,1f)
    }

    fun playAlienExplode(){
        mSP.play(mAlienExplodeID, 1f,1f,0,0,1f)
    }

    fun playPlayerExplode(){
        mSP.play(mPlayerExplodeID, 1f,1f,0,0,1f)
    }
}