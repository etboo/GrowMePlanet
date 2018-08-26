package com.etb.growmyplanet.screens.game.views

import com.badlogic.gdx.scenes.scene2d.Actor

/**
 * Created by etb on 22.08.2018.
 */
interface BaseView {

    fun show()

    fun hide()

    fun changePosition(x: Float, y: Float)

    fun resize(newSize: Float)

    fun isShown(): Boolean

    fun Actor.setCenterPosition(x: Float, y: Float) {
        this.setPosition(x - (width) / 2, y - (height) / 2)
    }
}