package com.etb.growmyplanet.screens.game.usecases

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Circle
import com.etb.growmyplanet.UseCase
import com.etb.growmyplanet.screens.game.behaviors.collider.Collision
import com.etb.growmyplanet.screens.game.behaviors.collider.Obstacle
import com.etb.growmyplanet.screens.game.behaviors.collider.Passed
import com.etb.growmyplanet.screens.game.models.ObstacleModel

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
                    Gdx.app.log("@", "collision detected $circle")
                    collisionAction.invoke(result.obstacleModel)
                    return false
                }
                is Passed -> {
                    Gdx.app.log("@", "passed $circle")
                    passedAction.invoke(result.obstacleModel)
                }
            }
        }

        return true
    }
}