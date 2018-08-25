package com.yarrtest.balloon.screens.game.behaviors.stage_related

import com.badlogic.gdx.math.Circle
import com.yarrtest.balloon.screens.game.behaviors.PlanetBehavior
import com.yarrtest.balloon.screens.game.models.PlanetModel
import com.yarrtest.balloon.screens.game.usecases.PlanetMoveCheckUseCase
import javax.inject.Inject

/**
 * Created by etb on 22.08.2018.
 */

private const val VELOCITY = 70f

class FloatingPlanetBehavior @Inject constructor (
        model: PlanetModel,
        private val moveValidator: PlanetMoveCheckUseCase
): PlanetBehavior(
        model
) {

    override fun act(delta: Float) {
        super.act(delta)
        model.moveBy(0f, delta * VELOCITY)
    }

    override fun interceptPositionChanged(x: Float, y: Float): Boolean {
        return moveValidator.invoke(model.toCircle())
    }

    private fun PlanetModel.toCircle() = Circle(this.x, this.y, this.radius)
}