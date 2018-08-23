package com.yarrtest.balloon.screens.game.di

import com.yarrtest.balloon.managers.level.LevelModel
import com.yarrtest.balloon.screens.game.GameController
import dagger.Subcomponent

@SessionScope
@Subcomponent(modules = [GameController::class, LevelModel::class])
interface GameSessionComponent {

    fun inject(controller: GameController)
}