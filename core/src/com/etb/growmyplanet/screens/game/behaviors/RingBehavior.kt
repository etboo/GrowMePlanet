package com.etb.growmyplanet.screens.game.behaviors

import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Vector2
import com.etb.growmyplanet.UseCase
import com.etb.growmyplanet.screens.game.REGISTER_OBSTACLE_USE_CASE
import com.etb.growmyplanet.screens.game.UNREGISTER_OBSTACLE_USE_CASE
import com.etb.growmyplanet.screens.game.behaviors.collider.*
import com.etb.growmyplanet.screens.game.di.LevelScope
import com.etb.growmyplanet.screens.game.models.RingModel
import com.etb.growmyplanet.screens.game.views.Ring
import javax.inject.Inject
import javax.inject.Named

@LevelScope
class RingBehavior @Inject constructor(
        model: RingModel,
        @Named (REGISTER_OBSTACLE_USE_CASE)
        registerObstacle: UseCase<@JvmWildcard Obstacle, @JvmWildcard Unit>,
        @Named(UNREGISTER_OBSTACLE_USE_CASE)
        unregisterObstacle: UseCase<@JvmWildcard Obstacle, @JvmWildcard Unit>
) : ObstacleBehavior<Ring, RingModel>(
        model,
        registerObstacle,
        unregisterObstacle
) {

    override fun collide(target: Circle): CollisionResult {
        val distanceFromEdgeToCenter = getDistanceFromEdgeToTargetCenter(target)
        return when {
            distanceFromEdgeToCenter < target.radius -> Collision(model)
            model.y > target.y -> None()
            else -> {
                if(model.y <= target.y) {
                    unregisterObstacle.invoke(this)
                    Passed(model)
                } else {
                    None()
                }
            }
        }
    }

    private fun getDistanceFromEdgeToTargetCenter(circle: Circle): Float {
        val edgePoint = Vector2(model.x + model.radius, model.y)

        val x = (circle.x - edgePoint.x).toDouble()
        val y = (circle.y - edgePoint.y).toDouble()

        return Math.sqrt(x * x + y * y).toFloat()
    }
}




