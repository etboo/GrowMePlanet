package com.yarrtest.balloon.screens.game.behaviors

import com.badlogic.gdx.math.Polyline
import com.yarrtest.balloon.managers.level.RING_MODEL
import com.yarrtest.balloon.screens.game.models.GameObjectModel
import com.yarrtest.balloon.screens.game.views.Ring
import javax.inject.Inject
import javax.inject.Named

private fun GameObjectModel.transformToVertices()
        = floatArrayOf( this.x - this.radius, this.y, this.x + this.radius, this.y)

class RingBehavior @Inject constructor(
        @Named(RING_MODEL) model: GameObjectModel,
        view: Ring
) : BaseBehavior<Polyline, Ring>(
        Polyline(model.transformToVertices()),
        view
) {
    override fun updateViewPosition(collider: Polyline) {
        val x = (collider.vertices[0] + collider.vertices[2]) / 2
        val y =  (collider.vertices[1] + collider.vertices[3]) / 2

        view.setPosition(x, y)
    }


}




