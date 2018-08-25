package com.yarrtest.balloon.screens.game.models

/**
 * Created by etb on 25.08.2018.
 */
interface ModelObserver {

    fun positionChanged(x: Float, y: Float)

    fun sizeChanged(width: Float, height: Float)
}