package com.yarrtest.balloon.screens.game.behaviors

import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Intersector
import com.badlogic.gdx.math.Vector2
import com.yarrtest.balloon.UseCase
import com.yarrtest.balloon.screens.game.REGISTER_OBSTACLE_USE_CASE
import com.yarrtest.balloon.screens.game.SCORED_USE_CASE
import com.yarrtest.balloon.screens.game.UNREGISTER_OBSTACLE_USE_CASE
import com.yarrtest.balloon.screens.game.behaviors.collider.CollideResult
import com.yarrtest.balloon.screens.game.behaviors.collider.Obstacle
import com.yarrtest.balloon.screens.game.di.LevelScope
import com.yarrtest.balloon.screens.game.models.ObstacleModel
import com.yarrtest.balloon.screens.game.models.RingModel
import com.yarrtest.balloon.screens.game.views.Ring
import javax.inject.Inject
import javax.inject.Named

private fun RingModel.transformToVertices()
        = floatArrayOf( this.x - this.radius, this.y, this.x + this.radius, this.y)

@LevelScope
class RingBehavior @Inject constructor(
        model: RingModel,
        @Named (REGISTER_OBSTACLE_USE_CASE)
        registerObstacle: UseCase<@JvmWildcard Obstacle, @JvmWildcard Unit>,
        @Named(UNREGISTER_OBSTACLE_USE_CASE)
        private val unregisterObstacle: UseCase<@JvmWildcard Obstacle, @JvmWildcard Unit>,
        @Named(SCORED_USE_CASE)
        private val scoredUseCase: UseCase<@JvmWildcard ObstacleModel, @JvmWildcard Unit>
) : BaseBehavior<Ring, RingModel>(
        model
), Obstacle {

    init {
        registerObstacle.invoke(this)
    }

    override fun detachView() {
        view?.hide()
        super.detachView()

    }

    override fun attachView(view: Ring) {
        super.attachView(view)
        view.show()
    }

    override fun collide(shape: Circle): CollideResult {
        val vertices = model.transformToVertices()
        val displace = Vector2()

        val result = Intersector.intersectSegmentCircleDisplace(
                Vector2(vertices[0], vertices[1]),
                Vector2(vertices[2], vertices[3]),
                Vector2(shape.x, shape.y),
                shape.radius,
                displace
        )

        return if(result == Float.POSITIVE_INFINITY) {
            CollideResult.NONE
        } else if(displace.x >= model.radius) {
            CollideResult.COLLIDED
        } else if(shape.y >= model.y) {
            unregisterObstacle.invoke(this)
            scoredUseCase.invoke(model)
            CollideResult.PASSED
        } else {
            CollideResult.NONE
        }
    }

    override fun dispose() {
        super.dispose()
        unregisterObstacle.invoke(this)

    }
}




