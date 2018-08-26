package com.yarrtest.balloon.screens.game.behaviors.stage_related.di

import com.yarrtest.balloon.screens.game.behaviors.stage_related.*
import javax.inject.Inject

/**
 * Created by etb on 25.08.2018.
 */

class PlanetBehaviorProvider(val stage: Stage) {

    @Inject
    protected lateinit var floatingImpl: dagger.Lazy<FloatingPlanetBehavior>

    @Inject
    protected lateinit var growingImpl: dagger.Lazy<GrowingPlanetBehavior>

    @Inject
    protected lateinit var failedFactoryImpl: FailedPlanetBehaviorFactory

    val planetBehavior by lazy {
        when(stage) {
            is GrowingStage -> growingImpl.get()
            is FloatingStage -> floatingImpl.get()
            is LevelFailed ->  failedFactoryImpl.createBehavior(stage.failedReason)
            else -> throw IllegalStateException("can't handle $stage yet")
        }
    }

}