package com.etb.growmyplanet

import com.badlogic.gdx.scenes.scene2d.Actor

fun Actor.setCenterPosition(x: Float, y: Float) {
    val leftX = x - width  / 2
    val bottomY = y - height / 2
    this.setPosition(leftX, bottomY)
}