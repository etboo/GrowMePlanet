package com.yarrtest.balloon.screens.game.behaviors

import com.yarrtest.balloon.screens.game.models.PlanetModel
import com.yarrtest.balloon.screens.game.views.Planet

/**
 * Created by etb on 25.08.2018.
 */
abstract class PlanetBehavior(model: PlanetModel)
    : BaseBehavior<Planet, PlanetModel>(model)