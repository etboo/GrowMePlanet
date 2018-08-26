package com.etb.growmyplanet.screens.game.behaviors

import com.etb.growmyplanet.screens.game.models.PlanetModel
import com.etb.growmyplanet.screens.game.views.Planet

/**
 * Created by etb on 25.08.2018.
 */
abstract class PlanetBehavior(model: PlanetModel)
    : BaseBehavior<Planet, PlanetModel>(model)