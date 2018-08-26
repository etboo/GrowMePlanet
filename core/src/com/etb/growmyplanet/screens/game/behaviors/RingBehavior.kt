package com.etb.growmyplanet.screens.game.behaviors

import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Intersector
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
        val vertices = model.transformToVertices()
        val displace = Vector2()

        val result = Intersector.intersectSegmentCircleDisplace(
                Vector2(vertices[0], vertices[1]),
                Vector2(vertices[2], vertices[3]),
                Vector2(target.x, target.y),
                target.radius,
                displace
        )

        return when {
            result == Float.POSITIVE_INFINITY -> None()
            displace.x >= model.radius -> Collision(model)
            target.y >= model.y -> {
                unregisterObstacle.invoke(this)
                Passed(model)
            }
            else -> None()
        }
    }
}

private fun RingModel.transformToVertices()
        = floatArrayOf( this.x - this.radius, this.y, this.x + this.radius, this.y)




