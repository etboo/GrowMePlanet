package com.etb.growmyplanet

import com.etb.growmyplanet.base.AppConfig
import com.etb.growmyplanet.base.BaseGame
import com.etb.growmyplanet.di.DaggerGameComponent
import com.etb.growmyplanet.di.GameComponent
import com.etb.growmyplanet.di.Managers
import com.etb.growmyplanet.screens.game.GameScreen
import com.etb.growmyplanet.services.StoreService
import com.google.gson.Gson

/**
 * Created by etb on 21.08.2018.
 */
class GrowMyPlanetGame(
        private val store: StoreService,
        private val config: AppConfig
): BaseGame(config) {

    var component: GameComponent? = null
    private set

    override fun create() {
        super.create()
        initGraph()
        setScreen()
    }

    override fun dispose() {
        super.dispose()
        component = null
    }

    private fun initGraph() {
        component = DaggerGameComponent.builder()
                .game(this)
                .managers(Managers())
                .gson(Gson())
                .storeService(store)
                .build()
    }

    private fun setScreen() {
        setScreen(GameScreen(this, config))
    }
}