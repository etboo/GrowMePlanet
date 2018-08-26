package com.etb.growmyplanet.screens.game.views

/**
 * Created by etb on 22.08.2018.
 */
interface BaseView {

    fun show()

    fun hide()

    fun setPosition(x: Float, y: Float)

    fun resize(newWidth: Float, newHeight: Float)

    fun isShown(): Boolean
}