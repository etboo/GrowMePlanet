package com.etb.growmyplanet.managers.level

const val START_PLAYER_RADIUS = 30f
const val START_PLAYER_Y = 200f
const val START_PLAYER_X = 360f

class LevelsCollection(
        val levels: List<LevelModel>
)

//@Module
//data class LevelModel(
//        @SerializedName("ring")
//        private val ring: RingModel,
//        @SerializedName("black_hole")
//        private val blackHole: BlackHoleModel,
//        private val player: PlanetModel =
//                PlanetModel(
//                        START_PLAYER_X,
//                        START_PLAYER_Y,
//                        START_PLAYER_RADIUS)
//) {
//
//    @LevelScope
//    @Provides
//    fun providePlayerModel(): PlanetModel = player
//
//    @LevelScope
//    @Provides
//    fun provideBlackHoleModel(): BlackHoleModel = blackHole
//
//    @LevelScope
//    @Provides
//    fun provideRingModel() = ring

//}