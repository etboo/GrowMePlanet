package com.yarrtest.balloon.screens.game.usecases

import com.yarrtest.balloon.UseCase
import com.yarrtest.balloon.managers.ScoreManager
import com.yarrtest.balloon.screens.game.SCORE_PER_RING


class CollideDetectedUseCase(
        private val scoreManager: ScoreManager
): UseCase<Unit, Unit> {

    override fun invoke(p1: Unit) {
        scoreManager.scored(SCORE_PER_RING)
    }

}