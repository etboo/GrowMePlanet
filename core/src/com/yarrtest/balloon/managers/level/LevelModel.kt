package com.yarrtest.balloon.managers.level

import com.yarrtest.balloon.screens.game.di.SessionScope
import com.yarrtest.balloon.screens.game.models.GameObjectModel
import dagger.Module
import dagger.Provides
import javax.inject.Named

const val PLANET_MODEL = "planet_model"
const val BLACK_HOLE_MODEL = "black_hole_model"
const val RING_MODEL = "ring_model"

@Module
data class LevelModel(
        private val player: GameObjectModel,
        private val blackHole: GameObjectModel,
        private val ring: GameObjectModel
) {

    @Named(PLANET_MODEL)
    @SessionScope
    @Provides
    fun providePlayerModel(): GameObjectModel = player

    @Named(BLACK_HOLE_MODEL)
    @SessionScope
    @Provides
    fun provideBlackHoleModel() = blackHole

    @Named(RING_MODEL)
    @SessionScope
    @Provides
    fun provideRingModel() = ring

}