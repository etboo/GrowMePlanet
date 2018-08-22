package com.yarrtest.balloon.screens.game.behaviors

import com.badlogic.gdx.math.Circle
import com.yarrtest.balloon.screens.game.models.ObjectModel
import com.yarrtest.balloon.screens.game.views.Balloon

/**
 * Created by etb on 22.08.2018.
 */
class BalloonBehavior(
        collider: ObjectModel,
        view: Balloon
): BaseBehavior<Circle, Balloon>(
        Circle(),
        view
) {

    override fun act(delta: Float) {
        super.act(delta)

    }

}