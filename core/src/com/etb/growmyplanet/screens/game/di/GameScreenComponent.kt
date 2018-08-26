package com.etb.growmyplanet.screens.game.di

import com.etb.growmyplanet.managers.level.LevelModel
import com.etb.growmyplanet.screens.di.ScreenScope
import com.etb.growmyplanet.screens.game.GameController
import com.etb.growmyplanet.screens.game.GameScreen
import dagger.Subcomponent

@ScreenScope
@Subcomponent(modules = [GameScreen::class, ControllersProvider::class])
interface GameScreenComponent {

    fun plus(
            useCases: GameController,
            models: LevelModel
    ): GameLevelComponent

    fun inject(screen: GameScreen)
}