package com.etb.growmyplanet.screens.game.behaviors.stage_related

import com.etb.growmyplanet.UseCase
import com.etb.growmyplanet.screens.game.PASSED_ANIMATION_FINISHED_USE_CASE
import com.etb.growmyplanet.screens.game.behaviors.PlanetBehavior
import com.etb.growmyplanet.screens.game.models.PlanetModel
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by etb on 26.08.2018.
 */

class PassedPlanetBehavior @Inject constructor(
        model: PlanetModel,
        @Named(PASSED_ANIMATION_FINISHED_USE_CASE)
        private val animationFinishedListener: UseCase<@JvmWildcard Unit, @JvmWildcard Unit>
): PlanetBehavior(model) {

}