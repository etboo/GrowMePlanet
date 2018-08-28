package com.etb.growmyplanet.screens.game.behaviors

import com.etb.growmyplanet.UseCase
import com.etb.growmyplanet.screens.game.behaviors.collider.Obstacle
import com.etb.growmyplanet.screens.game.models.GameObjectModel
import com.etb.growmyplanet.screens.game.views.BaseView

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
        //unregisterObstacle.invoke(this)
    }
}