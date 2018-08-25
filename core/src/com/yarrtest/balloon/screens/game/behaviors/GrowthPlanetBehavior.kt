package com.yarrtest.balloon.screens.game.behaviors

import com.yarrtest.balloon.screens.game.models.PlanetModel
import com.yarrtest.balloon.screens.game.usecases.ScreenTouchesUseCase
import javax.inject.Inject

/**
 * Created by etb on 22.08.2018.
 */
class GrowthPlanetBehavior @Inject constructor(
        model: PlanetModel,
        globalTouches: ScreenTouchesUseCase
): PlanetBehavior(
        model
) {

    private var velocity: Float = 70f

    init {
        globalTouches.invoke(Unit)
    }

}