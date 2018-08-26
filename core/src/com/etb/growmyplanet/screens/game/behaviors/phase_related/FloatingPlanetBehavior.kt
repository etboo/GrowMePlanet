package com.etb.growmyplanet.screens.game.behaviors.phase_related

import com.badlogic.gdx.math.Circle
import com.etb.growmyplanet.screens.game.behaviors.PlanetBehavior
import com.etb.growmyplanet.screens.game.behaviors.phase_related.di.GamePhaseScope
import com.etb.growmyplanet.screens.game.models.PlanetModel
import com.etb.growmyplanet.screens.game.usecases.PlanetMoveCheckUseCase
import com.etb.growmyplanet.screens.game.views.Planet
import javax.inject.Inject

/**
 * Created by etb on 22.08.2018.
 */

private const val VELOCITY = 270f

@GamePhaseScope
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

    override fun attachView(view: Planet) {
        this.view = view
        setInitialViewProperties()
    }

    override fun validatePositionChanged(x: Float, y: Float): Boolean {
        return moveValidator.invoke(model.toCircle())
    }

    private fun PlanetModel.toCircle() = Circle(this.x, this.y, this.radius)
}