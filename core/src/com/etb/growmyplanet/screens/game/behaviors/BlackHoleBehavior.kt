package com.etb.growmyplanet.screens.game.behaviors

import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Vector2
import com.etb.growmyplanet.UseCase
import com.etb.growmyplanet.screens.game.REGISTER_OBSTACLE_USE_CASE
import com.etb.growmyplanet.screens.game.UNREGISTER_OBSTACLE_USE_CASE
import com.etb.growmyplanet.screens.game.behaviors.collider.*
import com.etb.growmyplanet.screens.game.di.LevelScope
import com.etb.growmyplanet.screens.game.models.BlackHoleModel
import com.etb.growmyplanet.screens.game.views.BlackHole
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by etb on 23.08.2018.
 */
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

    override fun collide(target: Circle): CollisionResult {
        val distance = getDistanceTo(Vector2(target.x, target.y))
        return if(distance > model.radius) {
            None()
        } else if(model.radius < target.radius){
            unregisterObstacle.invoke(this)
            Passed(model)
        } else {
            Collision(model)
        }
    }

    private fun getDistanceTo(circleCenter: Vector2): Float {
        val x = (model.x - circleCenter.x).toDouble()
        val y = (model.y - circleCenter.y).toDouble()

        return Math.sqrt(x * x + y * y).toFloat()
    }


}