package com.yarrtest.balloon.screens.game.stages

import com.yarrtest.balloon.screens.game.behaviors.PlanetBehavior

/**
 * Created by etb on 25.08.2018.
 */
interface StageRelatedBehaviors {

    fun providePlanetBehavior(): PlanetBehavior
    
}