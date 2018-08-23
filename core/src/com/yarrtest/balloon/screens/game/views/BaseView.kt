package com.yarrtest.balloon.screens.game.views

/**
 * Created by etb on 22.08.2018.
 */
interface BaseView {

    fun attach()

    fun detach()

    fun setPosition(x: Float, y: Float)

    fun setSize(value: Float)
}