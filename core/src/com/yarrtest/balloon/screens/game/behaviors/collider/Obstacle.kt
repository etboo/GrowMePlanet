package com.yarrtest.balloon.screens.game.behaviors.collider

import com.badlogic.gdx.math.Circle

/**
 * Created by etb on 25.08.2018.
 */
interface Obstacle {

    fun collide(target: Circle): CollisionResult
}