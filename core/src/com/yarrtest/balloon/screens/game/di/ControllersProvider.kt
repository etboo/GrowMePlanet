package com.yarrtest.balloon.screens.game.di

import com.yarrtest.balloon.managers.ScoreManager
import com.yarrtest.balloon.managers.level.LevelManager
import com.yarrtest.balloon.screens.di.ScreenScope
import com.yarrtest.balloon.screens.game.GameController
import dagger.Module
import dagger.Provides

@Module
class ControllersProvider {

    @ScreenScope
    @Provides
    fun createController(scoreManager: ScoreManager, levelManager: LevelManager): GameController {
        return GameController(scoreManager, levelManager)
    }

}