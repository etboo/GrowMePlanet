package com.yarrtest.balloon.screens.game.behaviors.stage_related.di

import com.yarrtest.balloon.screens.game.behaviors.PlanetBehavior
import com.yarrtest.balloon.screens.game.behaviors.stage_related.GrowingPlanetBehavior
import com.yarrtest.balloon.screens.game.behaviors.stage_related.StageRelatedBehaviors
import dagger.Module
import javax.inject.Inject

/**
 * Created by etb on 25.08.2018.
 */
@Module
class GrowingStageBehaviors: StageRelatedBehaviors {

    @Inject
    lateinit var planetBehavior: GrowingPlanetBehavior

    override fun providePlanetBehavior(): PlanetBehavior {
        return planetBehavior
    }

}