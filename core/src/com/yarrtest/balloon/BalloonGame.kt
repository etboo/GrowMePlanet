package com.yarrtest.balloon

import com.yarrtest.balloon.base.AppConfig
import com.yarrtest.balloon.base.BaseGame
import com.yarrtest.balloon.di.DaggerGameComponent
import com.yarrtest.balloon.di.GameComponent
import com.yarrtest.balloon.di.Managers
import com.yarrtest.balloon.screens.GameScreen
import com.yarrtest.balloon.services.StoreService

/**
 * Created by etb on 21.08.2018.
 */
class BalloonGame(
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
                .storeService(store)
                .build()
    }

    private fun setScreen() {
        setScreen(GameScreen(this, config))
    }
}