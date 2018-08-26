package com.yarrtest.balloon.screens.game.behaviors

import com.yarrtest.balloon.UseCase
import com.yarrtest.balloon.screens.game.behaviors.collider.Obstacle
import com.yarrtest.balloon.screens.game.models.GameObjectModel
import com.yarrtest.balloon.screens.game.views.BaseView

/**
 * Created by etb on 26.08.2018.
 */
abstract class ObstacleBehavior<V: BaseView, M: GameObjectModel>(
        model: M,
        registerObstacle: UseCase<Obstacle, Unit>,
        protected val unregisterObstacle: UseCase<Obstacle, Unit>
): BaseBehavior<V, M> (
        model
), Obstacle {

    init {
        registerObstacle.invoke(this)
    }

    override fun dispose() {
        super.dispose()
        unregisterObstacle.invoke(this)
    }
}