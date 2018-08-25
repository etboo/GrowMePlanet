package com.yarrtest.balloon.screens.game.usecases

import com.badlogic.gdx.math.Circle
import com.yarrtest.balloon.UseCase
import com.yarrtest.balloon.screens.game.behaviors.collider.CollideResult
import com.yarrtest.balloon.screens.game.behaviors.collider.Obstacle

class PlanetMoveCheckUseCase(
        private val colliderProvider: () -> List<Obstacle>
) : UseCase<Circle, Boolean> {
    override fun invoke(circle: Circle): Boolean {
        colliderProvider.invoke().forEach {
            it.collide(circle).let {
                if (it == CollideResult.COLLIDED) {
                    return false
                }
            }
        }

        return true
    }
}