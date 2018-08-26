package com.etb.growmyplanet.managers.level

import com.etb.growmyplanet.screens.game.di.LevelScope
import com.etb.growmyplanet.screens.game.models.BlackHoleModel
import com.etb.growmyplanet.screens.game.models.PlanetModel
import com.etb.growmyplanet.screens.game.models.RingModel
import com.google.gson.annotations.SerializedName
import dagger.Module
import dagger.Provides

class LevelsCollection(
        val levels: List<LevelModel>
)

@Module
data class LevelModel(
        @SerializedName("planet")
        private val player: PlanetModel,
        @SerializedName("ring")
        private val ring: RingModel,
        @SerializedName("black_hole")
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