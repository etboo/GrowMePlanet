package com.yarrtest.balloon.base

/**
 * Created by etb on 21.08.2018.
 */
open interface AppConfig {

    val isDebug: Boolean
    val minDeltaTime: Float

    val camWidth: Float
    val camHeight: Float
}