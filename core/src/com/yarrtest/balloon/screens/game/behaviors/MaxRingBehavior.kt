package com.yarrtest.balloon.screens.game.behaviors

import com.badlogic.gdx.math.Polyline
import com.yarrtest.balloon.screens.game.models.ObjectModel
import com.yarrtest.balloon.screens.game.views.Ring

/**
 * Created by etb on 22.08.2018.
 */
class MaxRingBehavior(
        collider: ObjectModel,
        view: Ring
): BaseBehavior<Polyline, Ring>(
        Polyline(),
        view
) {

}