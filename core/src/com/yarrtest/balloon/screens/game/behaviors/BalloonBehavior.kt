package com.yarrtest.balloon.screens.game.behaviors

import com.badlogic.gdx.math.Circle
import com.yarrtest.balloon.managers.level.PLAYER_MODEL
import com.yarrtest.balloon.screens.game.models.GameObjectModel
import com.yarrtest.balloon.screens.game.usecases.BalloonMoveCheckUseCase
import com.yarrtest.balloon.screens.game.usecases.CollideDetectedUseCase
import com.yarrtest.balloon.screens.game.views.Balloon
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by etb on 22.08.2018.
 */
class BalloonBehavior @Inject constructor(
        @Named(PLAYER_MODEL) model: GameObjectModel,
        view: Balloon,
        private val moveValidator: BalloonMoveCheckUseCase,
        private val collideDetected: CollideDetectedUseCase
): BaseBehavior<Circle, Balloon>(
        Circle(model.x, model.y, model.radius),
        view
) {

    private var velocity: Float = 70f

    override fun act(delta: Float) {
        super.act(delta)

        collider.y += velocity * delta
        if(moveValidator.invoke(collider)) {
            updateViewPosition()
        } else {
            collideDetected.invoke(Unit)
        }
    }

    override fun updateViewPosition(model: Circle) {
        view.setPosition(model.x, model.y)
    }

    private fun updateViewPosition() {
        view.setPosition(collider.x, collider.y)
    }
}