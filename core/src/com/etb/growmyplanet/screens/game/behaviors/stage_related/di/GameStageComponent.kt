package com.etb.growmyplanet.screens.game.behaviors.stage_related.di

import dagger.Subcomponent

/**
 * Created by etb on 25.08.2018.
 */
@StageScope
@Subcomponent
interface GameStageComponent {

    fun inject(provider: PlanetBehaviorProvider)

}