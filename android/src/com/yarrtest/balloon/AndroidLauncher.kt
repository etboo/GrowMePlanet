package com.yarrtest.balloon

import android.os.Bundle
import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.yarrtest.balloon.services.EmptyStoreServiceImpl

class AndroidLauncher : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = AndroidApplicationConfiguration()
        initialize(createGame(), config)
    }

    private fun createGame(): ApplicationListener {
        return GrowMePlanetGame(
                EmptyStoreServiceImpl(),
                AndroidConfig()
        )
    }
}
