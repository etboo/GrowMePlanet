package com.yarrtest.balloon.screens.game.behaviors

import com.badlogic.gdx.math.Circle
import com.yarrtest.balloon.screens.game.models.PlanetModel
import com.yarrtest.balloon.screens.game.usecases.PlanetMoveCheckUseCase
import javax.inject.Inject

/**
 * Created by etb on 22.08.2018.
 */
class FloatingPlanetBehavior @Inject constructor (
        model: PlanetModel,
        private val moveValidator: PlanetMoveCheckUseCase
): PlanetBehavior(
        model
) {

    private var velocity: Float = 70f

    override fun act(delta: Float) {
        super.act(delta)
        model.moveBy(0f, velocity * delta)
    }

    override fun interceptPositionChanged(x: Float, y: Float): Boolean {
        return moveValidator.invoke(model.toCircle())
    }

    private fun PlanetModel.toCircle() = Circle(this.x, this.y, this.radius)
}