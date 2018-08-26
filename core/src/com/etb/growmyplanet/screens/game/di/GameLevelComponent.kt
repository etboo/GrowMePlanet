package com.etb.growmyplanet.screens.game.di

import com.etb.growmyplanet.managers.level.LevelModel
import com.etb.growmyplanet.screens.game.GameController
import com.etb.growmyplanet.screens.game.behaviors.stage_related.di.GameStageComponent
import dagger.Subcomponent

@LevelScope
@Subcomponent(modules = [GameController::class, LevelModel::class])
interface GameLevelComponent {

    fun stage(): GameStageComponent

    fun inject(controller: GameController)
}