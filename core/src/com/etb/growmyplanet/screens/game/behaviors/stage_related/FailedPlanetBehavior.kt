package com.etb.growmyplanet.screens.game.behaviors.stage_related

import com.etb.growmyplanet.UseCase
import com.etb.growmyplanet.screens.game.FAILED_ANIMATION_FINISHED_USE_CASE
import com.etb.growmyplanet.screens.game.behaviors.PlanetBehavior
import com.etb.growmyplanet.screens.game.behaviors.stage_related.di.StageScope
import com.etb.growmyplanet.screens.game.models.PlanetModel
import com.etb.growmyplanet.screens.game.views.Planet
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by etb on 26.08.2018.
 */
@StageScope
class FailedPlanetBehaviorFactory @Inject constructor(
        private val model: PlanetModel,
        @Named(FAILED_ANIMATION_FINISHED_USE_CASE)
        private val animationFinishedListener: UseCase<@JvmWildcard Unit, @JvmWildcard Unit>
) {
    fun createBehavior(failedReason: FailedReason): FailedPlanetBehavior {
        return FailedPlanetBehavior(model, failedReason, animationFinishedListener)
    }
}

class FailedPlanetBehavior(
        model: PlanetModel,
        private val failedReason: FailedReason,
        animationFinishedListener: UseCase<Unit, Unit>
): PlanetBehavior(model) {

    override fun attachView(view: Planet) {
        super.attachView(view)
        when(failedReason) {
            is Absorption -> {
                view.startAbsorbAnimation(failedReason.absorptionCenter)
            }
            is Collision -> {
                view.startFallingAnimation()
            }
        }
    }
}