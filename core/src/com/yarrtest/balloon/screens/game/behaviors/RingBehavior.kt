package com.yarrtest.balloon.screens.game.behaviors

import com.badlogic.gdx.math.Polyline
import com.yarrtest.balloon.screens.game.models.RingModel
import com.yarrtest.balloon.screens.game.views.Ring
import javax.inject.Inject

private fun RingModel.transformToVertices()
        = floatArrayOf( this.x - this.radius, this.y, this.x + this.radius, this.y)

class RingBehavior @Inject constructor(
        model: RingModel
) : ColliderBehavior<Ring, RingModel>(
        model
) {

    override val collider = Polyline(model.transformToVertices())

    override fun interceptPositionChanged(x: Float, y: Float): Boolean {
        //TODO: change collider
        return super.interceptPositionChanged(x, y)
    }

}




