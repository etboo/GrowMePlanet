package com.etb.growmyplanet.screens.game.behaviors

import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Vector2
import com.etb.growmyplanet.UseCase
import com.etb.growmyplanet.screens.game.REGISTER_OBSTACLE_USE_CASE
import com.etb.growmyplanet.screens.game.UNREGISTER_OBSTACLE_USE_CASE
import com.etb.growmyplanet.screens.game.behaviors.collider.Collision
import com.etb.growmyplanet.screens.game.behaviors.collider.CollisionResult
import com.etb.growmyplanet.screens.game.behaviors.collider.None
import com.etb.growmyplanet.screens.game.behaviors.collider.Obstacle
import com.etb.growmyplanet.screens.game.behaviors.collider.Passed
import com.etb.growmyplanet.screens.game.di.LevelScope
import com.etb.growmyplanet.screens.game.models.BlackHoleModel
import com.etb.growmyplanet.screens.game.views.BlackHole
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by etb on 23.08.2018.
 */
private const val ROTATION_SPEED = -180f

@LevelScope
class BlackHoleBehavior @Inject constructor(
        model: BlackHoleModel,
        @Named(REGISTER_OBSTACLE_USE_CASE)
        registerObstacle: UseCase<@JvmWildcard Obstacle, @JvmWildcard Unit>,
        @Named(UNREGISTER_OBSTACLE_USE_CASE)
        unregisterObstacle: UseCase<@JvmWildcard Obstacle, @JvmWildcard Unit>

) : ObstacleBehavior<BlackHole, BlackHoleModel>(
        model,
        registerObstacle,
        unregisterObstacle
) {

    override fun act(delta: Float) {
        view?.rotateBy(delta * ROTATION_SPEED)
    }

    override fun collide(target: Circle): CollisionResult {
        val distance = getDistanceTo(Vector2(target.x, target.y))
        return when {
            distance > Math.abs(model.radius - target.radius) -> None()
            model.radius < target.radius -> {
                unregisterObstacle.invoke(this)
                Passed(model)
            }
            else -> Collision(model)
        }
    }

    protected fun getDistanceTo(circleCenter: Vector2): Float {
        val x = (model.x - circleCenter.x).toDouble()
        val y = (model.y - circleCenter.y).toDouble()

        return Math.sqrt(x * x + y * y).toFloat()
    }
}