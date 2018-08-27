package com.etb.growmyplanet.screens.game.views

/**
 * Created by etb on 22.08.2018.
 */
interface BaseView {

    fun show()

    fun hide()

    fun changePosition(x: Float, y: Float)

    fun resize(newSize: Float)

    fun isShown(): Boolean
}
