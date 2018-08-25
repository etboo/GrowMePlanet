package com.yarrtest.balloon.managers.level

import com.yarrtest.balloon.screens.game.di.LevelScope
import com.yarrtest.balloon.screens.game.models.BlackHoleModel
import com.yarrtest.balloon.screens.game.models.PlanetModel
import com.yarrtest.balloon.screens.game.models.RingModel
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