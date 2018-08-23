package com.yarrtest.balloon.screens.game.di

import com.yarrtest.balloon.screens.GameScreen
import com.yarrtest.balloon.screens.di.ScreenScope
import com.yarrtest.balloon.screens.game.GameController
import dagger.Subcomponent

@ScreenScope
@Subcomponent(modules = [GameScreen::class])
interface GameScreenComponent {

    fun plus(controller: GameController): GameSessionComponent
}