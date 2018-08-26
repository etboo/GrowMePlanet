package com.etb.growmyplanet.screens.game.behaviors.phase_related.di

import dagger.Subcomponent

/**
 * Created by etb on 25.08.2018.
 */
@GamePhaseScope
@Subcomponent
interface GamePhaseComponent {

    fun inject(provider: PlanetBehaviorProvider)

}