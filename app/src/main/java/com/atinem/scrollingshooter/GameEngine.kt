package com.atinem.scrollingshooter

import android.content.Context
import android.graphics.Point
import android.graphics.PointF
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceView
import com.atinem.scrollingshooter.components.AlienLaserSpawner
import com.atinem.scrollingshooter.components.PlayerLaserSpawner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GameEngine(context: Context, size: Point) : SurfaceView(context), GameStarter, GameEngineBroadcaster, PlayerLaserSpawner, AlienLaserSpawner {



    private var mFPS : Long = 0
    private var job : Job? = null

    private val inputObserver : MutableList<InputObserver> = mutableListOf()
    val mUIController = UIController(this)

    private val mGameState = GameState(this, context)
    private val mSoundEngine = SoundEngine(context)
    private val mHUD : HUD = HUD(size)
    private val mRenderer = Renderer(this)
    val mParticleSystem = ParticleSystem()
    val mPhysicsEngine = PhysicsEngine()
    val mLevel: Level = Level(context, PointF(size.x.toFloat(), size.y.toFloat()),this)


    init {
        mParticleSystem.init(1000)
    }


    fun runJob(){
        job = GlobalScope.launch {
            while(mGameState.mThreadRunning){
                val frameStartTime = System.currentTimeMillis()
                val objects = mLevel.objects

                if(!mGameState.mPaused){
                    //Update all game objects here
                    if(mPhysicsEngine.update(mFPS, objects, mGameState, mSoundEngine, mParticleSystem)){
                        deSpawnwReSpawn()
                    }
                }
                //Draw all the game objects here
                //in a new way
                mRenderer.draw(objects, mGameState, mHUD, mParticleSystem)

                val timeThisFrame = System.currentTimeMillis() - frameStartTime
                if(timeThisFrame >= 1){
                    val MILLIS_IN_SECOND = 1000
                    mFPS = MILLIS_IN_SECOND / timeThisFrame
                }
            }

        }
    }

    fun startJob(){
        mGameState.startThread()
        runJob()
    }

    fun stopJob(){
        mGameState.stopEverything()
        job?.cancel()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //Handle the player's input here

        Log.d("OnTouchEvent", "onTouchEvent method")
        for(observer in inputObserver){
            observer.handleInput(event, mGameState, mHUD.controls)
        }

        return true
    }

    override fun deSpawnwReSpawn() {
        val objects: List<GameObject> = mLevel.objects

        for(gameObject in objects){
            gameObject.isActive = false
        }

        objects[Level.PLAYER_INDEX].spawn(objects[Level.PLAYER_INDEX].mTransform)

        objects[Level.BACKGROUND_INDEX].spawn(objects[Level.PLAYER_INDEX].mTransform)

        for(i in Level.FIRST_ALIEN..Level.LAST_ALIEN){
            objects[i].spawn(objects[Level.PLAYER_INDEX].mTransform)
        }
    }

    override fun addObserver(observer: InputObserver) {
        inputObserver.add(observer)
    }

    override fun spawnPlayerLaser(transform: Transform): Boolean {
        val objects = mLevel.objects

        if(objects[Level.mNextPlayerLaser].spawn(transform)){
            Level.mNextPlayerLaser++
            mSoundEngine.playShoot()
            if(Level.mNextPlayerLaser == Level.LAST_PLAYER_LASER+1){
                Level.mNextPlayerLaser = Level.FIRST_PLAYER_LASER
            }
        }

        return true
    }

    override fun spawnAlienLaser(transform: Transform) {
        val objects = mLevel.objects
        if(objects[Level.mNextAlienLaser].spawn(transform)){
            Level.mNextAlienLaser++
            mSoundEngine.playShoot()
            if(Level.mNextAlienLaser == Level.LAST_ALIEN_LASER +1){
                Level.mNextAlienLaser = Level.FIRST_ALIEN_LASER
            }
        }
    }
}