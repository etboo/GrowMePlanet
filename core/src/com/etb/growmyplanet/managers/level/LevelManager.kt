package com.etb.growmyplanet.managers.level

import com.etb.growmyplanet.screens.game.models.BlackHoleModel
import com.etb.growmyplanet.screens.game.models.PlanetModel
import com.etb.growmyplanet.screens.game.models.RingModel
import com.etb.growmyplanet.services.StoreService

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
                RingModel(200f, 500f, 20f, 20),
                BlackHoleModel(200f, 900f, 20f, 10)
        )
    }
}