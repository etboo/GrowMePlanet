package com.yarrtest.balloon.screens.game.behaviors.stage_related.di

import com.yarrtest.balloon.screens.game.behaviors.PlanetBehavior
import com.yarrtest.balloon.screens.game.behaviors.stage_related.FloatingPlanetBehavior
import com.yarrtest.balloon.screens.game.behaviors.stage_related.StageRelatedBehaviors
import javax.inject.Inject

/**
 * Created by etb on 25.08.2018.
 */
class FloatingStageBehaviors: StageRelatedBehaviors {

    @Inject
    lateinit var planetBehavior: FloatingPlanetBehavior

    override fun providePlanetBehavior(): PlanetBehavior {
        return planetBehavior
    }
}