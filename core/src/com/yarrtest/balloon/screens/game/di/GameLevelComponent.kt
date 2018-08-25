package com.yarrtest.balloon.screens.game.di

import com.yarrtest.balloon.managers.level.LevelModel
import com.yarrtest.balloon.screens.game.GameController
import com.yarrtest.balloon.screens.game.behaviors.stage_related.di.GameStageComponent
import dagger.Subcomponent

@LevelScope
@Subcomponent(modules = [GameController::class, LevelModel::class])
interface GameLevelComponent {

    fun stage(): GameStageComponent

    fun inject(controller: GameController)
}