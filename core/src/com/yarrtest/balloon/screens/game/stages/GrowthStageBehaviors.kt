package com.yarrtest.balloon.screens.game.stages

import com.yarrtest.balloon.screens.game.behaviors.GrowthPlanetBehavior
import com.yarrtest.balloon.screens.game.behaviors.PlanetBehavior
import dagger.Module
import javax.inject.Inject

/**
 * Created by etb on 25.08.2018.
 */
@Module
class GrowthStageBehaviors: StageRelatedBehaviors {

    @Inject
    lateinit var planetBehavior: GrowthPlanetBehavior

    override fun providePlanetBehavior(): PlanetBehavior {
        return planetBehavior
    }

}