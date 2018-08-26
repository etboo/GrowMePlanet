package com.yarrtest.balloon.screens.game.usecases

import com.badlogic.gdx.math.Circle
import com.yarrtest.balloon.UseCase
import com.yarrtest.balloon.screens.game.behaviors.collider.Collision
import com.yarrtest.balloon.screens.game.behaviors.collider.Obstacle
import com.yarrtest.balloon.screens.game.behaviors.collider.Passed
import com.yarrtest.balloon.screens.game.models.ObstacleModel

class PlanetMoveCheckUseCase(
        private val colliderProvider: () -> List<Obstacle>,
        private val passedAction: UseCase<ObstacleModel, Unit>,
        private val collisionAction: UseCase<ObstacleModel, Unit>
) : UseCase<Circle, Boolean> {
    override fun invoke(circle: Circle): Boolean {
        colliderProvider.invoke().forEach {
            val result = it.collide(circle)
            when(result) {
                is Collision -> {
                    collisionAction.invoke(result.obstacleModel)
                    return false
                }
                is Passed -> {
                    passedAction.invoke(result.obstacleModel)
                }
            }
        }

        return true
    }
}