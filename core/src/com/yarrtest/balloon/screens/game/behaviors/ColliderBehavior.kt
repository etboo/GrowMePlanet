package com.yarrtest.balloon.screens.game.behaviors

import com.badlogic.gdx.math.Polyline
import com.yarrtest.balloon.screens.game.models.GameObjectModel
import com.yarrtest.balloon.screens.game.views.BaseView

/**
 * Created by etb on 25.08.2018.
 */
abstract class ColliderBehavior<V: BaseView, out M: GameObjectModel>(
        model: M
): BaseBehavior<V, M>(model) {

    abstract val collider: Polyline
}