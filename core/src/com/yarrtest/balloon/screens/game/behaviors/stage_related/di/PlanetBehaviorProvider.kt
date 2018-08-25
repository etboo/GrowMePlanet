package com.yarrtest.balloon.screens.game.behaviors.stage_related.di

import com.yarrtest.balloon.screens.game.behaviors.stage_related.FloatingPlanetBehavior
import com.yarrtest.balloon.screens.game.behaviors.stage_related.GrowingPlanetBehavior
import com.yarrtest.balloon.screens.game.behaviors.stage_related.Stage
import javax.inject.Inject

/**
 * Created by etb on 25.08.2018.
 */

class PlanetBehaviorProvider(val stage: Stage) {

    @Inject
    protected lateinit var floatingImpl: dagger.Lazy<FloatingPlanetBehavior>

    @Inject
    protected lateinit var growingImpl: dagger.Lazy<GrowingPlanetBehavior>

    val planetBehavior by lazy {
        when(stage) {
            Stage.GROWING_STAGE -> growingImpl.get()
            Stage.FLOATING_STAGE -> floatingImpl.get()
        }
    }

}