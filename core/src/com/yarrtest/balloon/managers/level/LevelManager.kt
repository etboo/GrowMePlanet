package com.yarrtest.balloon.managers.level

import com.yarrtest.balloon.screens.game.models.BlackHoleModel
import com.yarrtest.balloon.screens.game.models.PlanetModel
import com.yarrtest.balloon.screens.game.models.RingModel
import com.yarrtest.balloon.services.StoreService

/**
 * Created by etb on 22.08.2018.
 */

class LevelManager(store: StoreService) {
    var currentLevel: Int = 1

    fun loadLevel(index: Int): LevelModel {
        currentLevel = index
        //TODO: load this model from file or config
        return LevelModel(
                PlanetModel(200f, 200f, 100f),
                RingModel(500f, 100f, 100f),
                BlackHoleModel(200f, 600f, 100f)
        )
    }
}