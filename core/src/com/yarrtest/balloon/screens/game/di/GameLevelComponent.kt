package com.yarrtest.balloon.screens.game.di

import com.yarrtest.balloon.managers.level.LevelModel
import com.yarrtest.balloon.screens.game.GameController
import com.yarrtest.balloon.screens.game.behaviors.stage_related.StageRelatedBehaviors
import dagger.Subcomponent

@LevelScope
@Subcomponent(modules = [GameController::class, LevelModel::class])
interface GameLevelComponent {

    fun inject(controller: GameController)

    fun inject(stageRelatedBehaviors: StageRelatedBehaviors)
}