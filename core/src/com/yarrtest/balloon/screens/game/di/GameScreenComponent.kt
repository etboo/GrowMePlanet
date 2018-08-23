package com.yarrtest.balloon.screens.game.di

import com.yarrtest.balloon.managers.level.LevelModel
import com.yarrtest.balloon.screens.di.ScreenScope
import com.yarrtest.balloon.screens.game.GameController
import com.yarrtest.balloon.screens.game.GameScreen
import dagger.Subcomponent

@ScreenScope
@Subcomponent(modules = [GameScreen::class, ControllersProvider::class])
interface GameScreenComponent {

    fun plus(
            useCases: GameController,
            models: LevelModel
    ): GameSessionComponent

    fun inject(screen: GameScreen)
}