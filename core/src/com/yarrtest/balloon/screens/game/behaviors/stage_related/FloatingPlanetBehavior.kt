package com.yarrtest.balloon.screens.game.behaviors.stage_related

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Circle
import com.yarrtest.balloon.screens.game.behaviors.PlanetBehavior
import com.yarrtest.balloon.screens.game.behaviors.stage_related.di.StageScope
import com.yarrtest.balloon.screens.game.models.PlanetModel
import com.yarrtest.balloon.screens.game.usecases.PlanetMoveCheckUseCase
import com.yarrtest.balloon.screens.game.views.Planet
import javax.inject.Inject

/**
 * Created by etb on 22.08.2018.
 */

private const val VELOCITY = 70f

@StageScope
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
        Gdx.app.log("@", "floating $x $y")
        return moveValidator.invoke(model.toCircle())
    }

    private fun PlanetModel.toCircle() = Circle(this.x, this.y, this.radius)
}