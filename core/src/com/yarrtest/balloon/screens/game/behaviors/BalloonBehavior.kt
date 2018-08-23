package com.yarrtest.balloon.screens.game.behaviors

import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Shape2D
import com.yarrtest.balloon.UseCase
import com.yarrtest.balloon.screens.game.models.ObjectModel
import com.yarrtest.balloon.screens.game.views.Balloon

/**
 * Created by etb on 22.08.2018.
 */
class BalloonBehavior(
        model: ObjectModel,
        view: Balloon,
        private val isMoveValidUseCase: UseCase<Shape2D, Boolean>,
        private val collideDetectedUseCase: UseCase<Unit, Unit>
): BaseBehavior<Circle, Balloon>(
        Circle(model.x, model.y, model.radius),
        view
) {

    private var velocity: Float = 70f

    override fun act(delta: Float) {
        super.act(delta)

        collider.y += velocity * delta
        if(isMoveValidUseCase.invoke(collider)) {
            updateViewPosition()
        } else {
            collideDetectedUseCase.invoke(Unit)
        }
    }

    override fun updateViewPosition(model: Circle) {
        view.setPosition(model.x, model.y)
    }

    private fun updateViewPosition() {
        view.setPosition(collider.x, collider.y)
    }
}