package com.yarrtest.balloon.screens.game.di

import com.yarrtest.balloon.screens.game.GameController
import dagger.Subcomponent

@GameScope
@Subcomponent(modules = [GameController::class])
interface GameSessionComponent {

    fun inject(controller: GameController)
}