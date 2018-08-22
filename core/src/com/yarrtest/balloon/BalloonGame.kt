package com.yarrtest.balloon

import com.yarrtest.balloon.base.AppConfig
import com.yarrtest.balloon.base.BaseGame
import com.yarrtest.balloon.screens.GameScreen
import com.yarrtest.balloon.services.StoreService

/**
 * Created by etb on 21.08.2018.
 */
class BalloonGame(
        private val store: StoreService,
        private val config: AppConfig
): BaseGame(config) {

    override fun create() {
        super.create()
        setScreen(GameScreen(this, config))
    }
}