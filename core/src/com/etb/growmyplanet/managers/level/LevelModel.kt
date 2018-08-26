package com.etb.growmyplanet.managers.level

import com.etb.growmyplanet.screens.game.di.LevelScope
import com.etb.growmyplanet.screens.game.models.BlackHoleModel
import com.etb.growmyplanet.screens.game.models.PlanetModel
import com.etb.growmyplanet.screens.game.models.RingModel
import dagger.Module
import dagger.Provides

@Module
data class LevelModel(
        private val player: PlanetModel,
        private val ring: RingModel,
        private val blackHole: BlackHoleModel
) {

    @LevelScope
    @Provides
    fun providePlayerModel(): PlanetModel = player

    @LevelScope
    @Provides
    fun provideBlackHoleModel(): BlackHoleModel = blackHole

    @LevelScope
    @Provides
    fun provideRingModel() = ring

}