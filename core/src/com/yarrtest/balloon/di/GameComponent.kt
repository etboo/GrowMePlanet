package com.yarrtest.balloon.di

import com.yarrtest.balloon.GrowMePlanetGame
import com.yarrtest.balloon.screens.game.GameScreen
import com.yarrtest.balloon.screens.game.di.ControllersProvider
import com.yarrtest.balloon.screens.game.di.GameScreenComponent
import com.yarrtest.balloon.services.StoreService
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [Managers::class])
interface GameComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun game(game: GrowMePlanetGame): Builder

        @BindsInstance
        fun storeService(store: StoreService): Builder

        @BindsInstance
        fun managers(manager: Managers): Builder

        fun build(): GameComponent
    }

    fun plus(
            screen: GameScreen,
            controllers: ControllersProvider
    ): GameScreenComponent

}