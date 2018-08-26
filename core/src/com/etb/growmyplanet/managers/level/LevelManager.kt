package com.etb.growmyplanet.managers.level

import com.badlogic.gdx.Gdx
import com.etb.growmyplanet.services.StoreService
import com.google.gson.Gson

/**
 * Created by etb on 22.08.2018.
 */
class LevelManager(
        private val store: StoreService,
        gson: Gson
) {
    var currentLevel = store.getInt(CURRENT_LEVEL_KEY, 0)
    val levels: List<LevelModel>

    init {
        levels = parseJson(gson)
    }

    fun loadLevel(index: Int): LevelModel {
        currentLevel = index
        return levels[index]
    }

    private fun parseJson(gson: Gson): List<LevelModel> {
        val reader = Gdx.files.internal(LEVELS_CONFIG_PATH)
                .reader()

        return gson.fromJson(
                reader,
                LevelsCollection::class.java
        ).levels
    }
}

private const val LEVELS_CONFIG_PATH = "config/levels.json"
private const val CURRENT_LEVEL_KEY = "com.etb.growmyplanet.manager.level.CurrentLevel"