package com.atinem.scrollingshooter

interface GameEngineBroadcaster {
    fun addObserver(observer : InputObserver)
}