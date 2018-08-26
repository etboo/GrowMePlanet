package com.etb.growmyplanet.screens.game.di

import com.etb.growmyplanet.managers.ScoreManager
import com.etb.growmyplanet.managers.level.LevelManager
import com.etb.growmyplanet.screens.di.ScreenScope
import com.etb.growmyplanet.screens.game.GameController
import com.etb.growmyplanet.screens.game.usecases.SwapLevelsUseCase
import dagger.Module
import dagger.Provides

@Module
class ControllersProvider {

    @ScreenScope
    @Provides
    fun createController(
            scoreManager: ScoreManager,
            levelManager: LevelManager,
            swapLevesUseCase: SwapLevelsUseCase
    ): GameController {
        return GameController(
                scoreManager,
                levelManager,
                swapLevesUseCase
        )
    }

}