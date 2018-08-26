package com.etb.growmyplanet.screens.game.di

import com.etb.growmyplanet.managers.level.LevelModel
import com.etb.growmyplanet.screens.game.GameController
import com.etb.growmyplanet.screens.game.behaviors.phase_related.di.GamePhaseComponent
import dagger.Subcomponent

@LevelScope
@Subcomponent(modules = [GameController::class, LevelModel::class])
interface GameLevelComponent {

    fun stage(): GamePhaseComponent

    fun inject(controller: GameController)
}