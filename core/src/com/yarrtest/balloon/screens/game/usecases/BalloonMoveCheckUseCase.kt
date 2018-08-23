package com.yarrtest.balloon.screens.game.usecases

import com.badlogic.gdx.math.Polyline
import com.badlogic.gdx.math.Shape2D
import com.yarrtest.balloon.UseCase


class BalloonMoveCheckUseCase(
        ringColliders: List<Polyline>
): UseCase<Shape2D, Boolean> {
    override fun invoke(p1: Shape2D): Boolean {
        return true
    }

}


