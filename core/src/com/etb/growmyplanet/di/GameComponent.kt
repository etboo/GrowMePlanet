package com.etb.growmyplanet.di

import com.etb.growmyplanet.GrowMyPlanetGame
import com.etb.growmyplanet.screens.game.GameScreen
import com.etb.growmyplanet.screens.game.di.ControllersProvider
import com.etb.growmyplanet.screens.game.di.GameScreenComponent
import com.etb.growmyplanet.services.StoreService
import com.google.gson.Gson
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [Managers::class])
interface GameComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun game(game: GrowMyPlanetGame): Builder

        @BindsInstance
        fun storeService(store: StoreService): Builder

        @BindsInstance
        fun managers(manager: Managers): Builder

        @BindsInstance
        fun gson(instance: Gson): Builder

        fun build(): GameComponent
    }

    fun plus(
            screen: GameScreen,
            controllers: ControllersProvider
    ): GameScreenComponent

}