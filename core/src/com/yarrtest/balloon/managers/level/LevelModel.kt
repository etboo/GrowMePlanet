package com.yarrtest.balloon.managers.level

import com.yarrtest.balloon.screens.game.di.SessionScope
import com.yarrtest.balloon.screens.game.models.GameObjectModel
import dagger.Module
import dagger.Provides
import javax.inject.Named

const val PLAYER_MODEL = "player_model"
const val MIN_RING_MODELS = "min_rings_model"
const val MAX_RING_MODELS = "max_rings_model"

@Module
data class LevelModel(
        private val player: GameObjectModel,
        private val minRings: List<GameObjectModel>,
        private val maxRings: List<GameObjectModel>
) {

    @Named(PLAYER_MODEL)
    @SessionScope
    @Provides
    fun providePlayerModel(): GameObjectModel = player

    @Named(MIN_RING_MODELS)
    @SessionScope
    @Provides
    fun provideMinRingModels() = minRings

    @Named(MAX_RING_MODELS)
    @SessionScope
    @Provides
    fun provideMaxRingModels() = maxRings

}